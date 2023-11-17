<template >
    <div class="col-lg-8 mx-auto p-5 py-md-5">
        <main class="row">
            <!--        상단 왼쪽(카테고리, 제목, 신청현황)-->
            <div class="col mb-2">

                <!--            카테고리(button 클릭 시 해당 카테고리의 meeting board 페이지로 이동)-->
                <div class="row justify-content-between mb-4" style="margin:2px;">
                    <a style="width:200px;" class="col-1 btn btn-outline-primary" @click="$router.push('/meetings')">{{ category }}</a>
                </div>

                <!--            미팅 제목-->
                <h1 class="fw-bold mb-2">{{ title }}</h1>

                <!--            미팅 현재인원/ 모집인원-->
                <div class="text-body-secondary mb-4">
                    <p class="fs-6">현재인원 / 모집인원 : {{ currentRecruits }} / {{ recruits }} | 시작일: {{
                        startDatetime.substring(0,
                            10) }}
                    </p>
                </div>
            </div>

            <!--        상단 오른쪽( 신청/스크랩 버튼 등 )-->
            <div class="col mb-2 position-relative" style="text-align: right;">
                <div v-if="writer">
                    <!--                삭제 & 수정 버튼 -->
                    <div class="btn-group position-absolute top-0 end-0" role="group"
                        aria-label="Basic mixed styles example" style="width:max-content;">
                        <button type="button" class="btn btn-danger" @click="deleteMeeting">삭제
                        </button>
                        <button type="button" class="btn btn-success"
                            th:onclick="|location.href='@{/updateMetForm.html}'|">수정
                        </button>
                    </div>
                    <!--                신청 현황 offcanvas 열기 버튼-->
                    <button id="currentApply" class="btn btn-primary position-absolute bottom-0 end-0 mb-4" type="button"
                        data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">신청
                        현황</button>
                </div>
                <!--            th:unless=${isWriter} -> 작성자가 아닐 경우-->
                <div v-else class="row justify-content-end">

                    <!--                and v-if=${apply} -> 해당 소모임에 참가 신청을 하지 않은 경우, 신청 버튼 보이기 -->
                    <!--                and v-if=${apply} -> 해당 소모임에 참가 신청을 한 경우, 신청 취소 버튼 보이기 -->
                    <div class="col-auto offset-md-4" v-if="!applicant">
                        <a class="btn btn-primary btn-lg px-4 btn-color"
                            @click="apply">신청</a>
                    </div>
                    <div class="col-auto offset-md-4" v-else>
                        <a class="btn btn-primary btn-lg px-4 btn-color"
                        @click="">신청 취소</a>
                    </div>

                    <!--                and th:if=${scrap} -> 해당 소모임에 스크랩을 하지 않은 경우, 스크랩 버튼 보이기 -->
                    <!--                and th:unless=${scrap} -> 해당 소모임에 스크랩을 한 경우, 스크랩 취소 버튼 보이기 -->
                    <div class="col-auto" v-if="!scrap">
                    <a class="btn btn-primary btn-lg px-4 btn-color"
                    @click="">스크랩</a>
                </div>
                <div class="col-auto" v-else>
                    <a class="btn btn-primary btn-lg px-4 btn-color"
                    @click="">스크랩 취소</a>
                </div>
                </div>
            </div>
            <hr class="mb-5">

            <!--        하단(이미지, 글 내용) -->
            <div class="meeting-contents">

                <!--            v-if="${meeting_image}" -> 이미지가 포함된 글일 경우-->
                <div v-if="images.length != 0" id="carouselExampleDark"
                    class="carousel carousel-dark slidez border border-dark-subtle mb-5" data-bs-ride="carousel">
                    <div class="carousel-inner position-absolute top-50 start-50 translate-middle">
                        <template v-for="(image, index) in images" :key="image">

                            <div class="carousel-item active" v-if="index == 0">
                                <img :src="server + image" class="d-block" alt="no image">
                            </div>
                            <div class="carousel-item" v-else>
                                <img :src="server + image" class="d-block" alt="no image">
                            </div>

                        </template>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target='#carouselExampleDark'
                        data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target='#carouselExampleDark'
                        data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
                <br>
                <div class="mb-5 fs-6" style="width:80%;" v-html="content.split('\n').join('<br />')"></div>
            </div>

        </main>
        <!--    신청 현황 offcanvas-->
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasRightLabel">신청 현황</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
                <div v-if="members.length != 0" class="offcanvas-body">
                    <template v-for="member in members">
                            <div class="card mb-2" style="width: 18rem;">
                                <div class="card-body">
                                    <h5 class="card-title">{{ member.nickname }}</h5>
                                    <p class="card-text">{{ member.category }}</p>
                                    <template v-if="member.accepted == 0">
                                        <a @click='acceptApply(true, member.idx, member.nickname)'
                                        class="btn btn-primary me-1">수락</a>
                                        <a @click="declineApply(member.idx, member.nickname)"
                                        class="btn btn-primary">거절</a>
                                    </template>
                                    <a v-else
                                    @click='acceptApply(false, member.idx, member.nickname)'
                                        class="btn btn-primary">수락 취소</a>
                                </div>
                            </div>
                        </template>
                </div>
                <div v-else class="offcanvas-body">
                        아직 신청한 사람이 없어요!
                </div>

            </div>

    </div>
</template>
<script setup>
import { ref, onBeforeMount } from 'vue';
import { api } from '@/common.js'

const idx = ref(0);
const title = ref('');
const content = ref('');
const category = ref('');
const recruits = ref(0);
const location = ref('');
const startDatetime = ref('');
const createdDatetime = ref('');
const currentRecruits = ref(0);
const images = ref([]);
const applicant = ref(false);
const writer = ref(false);
const scrap = ref(false);
const members = ref([]);
const server = "http://localhost:8090";
const meeting_server = server + "/meetings/";

const regex = /[^0-9]/g;
const url = document.location.pathname;
let meeting_idx;
if (url) {
    meeting_idx = url.replace(regex, "");

}

onBeforeMount(() => {
    api(meeting_server + meeting_idx, "GET", {})
        .then((resp) => {
            console.log(resp)
            idx.value = resp.idx;
            title.value = resp.title;
            content.value = resp.content;
            category.value = resp.category;
            recruits.value = resp.recruits;
            location.value = resp.location;
            startDatetime.value = resp.startDatetime;
            createdDatetime.value = resp.createdDatetime;
            currentRecruits.value = resp.currentRecruits;
            images.value = resp.images;
            applicant.value = resp.applicant;
            writer.value = resp.writer;
            scrap.value = resp.scrap;

            if(resp.writer){
                api(meeting_server + "applicants/" +meeting_idx, "GET", {})
                .then((member_resp)=> {
                    members.value = member_resp;
                    console.log(member_resp)
                })
            }
        })
})

const deleteMeeting = () => {
    if (window.confirm("이 글을 삭제하시겠습니까?")) {
        api(meeting_server + meeting_idx, "DELETE")
            .then((resp) => {

                window.alert(resp.message);
                document.location.href = "/meetings";
            })
    }
}

const apply = () => {
    if(window.confirm("이 모임에 신청 요청을 보내겠습니까?")){
        api(meeting_server + "apply", "POST", {
            "meeting_idx" : meeting_idx
        })
            .then((resp) => {
                window.alert(resp.message);
                document.location.href = "/meeting/" + meeting_idx;
            })
    }
}

const acceptApply = (accept, idx, nickname) => {
    const message = accept ? nickname + "님의 요청을 수락하시겠습니까?" : "수락을 취소하시겠습니까?";

    if (window.confirm(message)) {
        api(meeting_server + "apply/" + idx, "PUT", {})
            .then((resp) => {
                window.alert(resp.message);
                document.location.href = "/meeting/" + meeting_idx;
            })
    }
}

const declineApply = (idx, nickname) => {
    if (window.confirm(nickname + "님의 요청을 거절하시겠습니까?")) {
        api(meeting_server + "apply/" + idx, "DELETE", {})
            .then((resp) => {
                window.alert(resp.message);
                document.location.href = "/meeting/" + meeting_idx;
            })
    }
}

</script>

<style scoped>
@font-face {
    font-family: 'Pretendard-Regular'!important;
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400 !important;
    font-style: normal!important;
}

* {
    font-family: 'Pretendard-Regular' !important;
}

.btn-color {
    color: #5302FE !important;
}

.meeting-contents>* {
    margin: 0 auto !important;
}

a.btn.btn-primary.btn-lg.px-4.btn-color {
    --bs-btn-color: #5302FE !important;
    --bs-btn-bg: #fff !important;
    --bs-btn-border-color: #5302FE !important;
    --bs-btn-hover-border-color: white !important;
    --bs-btn-hover-bg: #a880ff !important;
    --bs-btn-active-bg: #a880ff !important;
    --bs-btn-active-border-color: #a880ff !important;
}
a.col-1.btn.btn-outline-primary {
    --bs-btn-color: #fff !important;
    --bs-btn-bg: #5302FE !important;
    --bs-btn-border-color: #5302FE !important;
    --bs-btn-hover-color: #5302FE !important;
    --bs-btn-hover-bg: white !important;
    --bs-btn-hover-border-color: #5302FE !important;
}

.col-md-auto a {
    width: 200px !important;
}

main {
    margin: auto !important;
}

.justify-content-end {
    height: fit-content !important;
}

#carouselExampleDark {
    height: 460px !important;
    width: 810px !important;
}

.carousel-item img {
    height: 450px !important;
    width: 800px !important;
    object-fit: contain !important;
}

#currentApply {
    --bs-btn-color: #fff !important;
    --bs-btn-bg: #5302FE !important;
    --bs-btn-border-color: #5302FE !important;
    --bs-btn-hover-color: #5302FE !important;
    --bs-btn-hover-bg: white !important;
    --bs-btn-hover-border-color: #5302FE !important;
}
</style>