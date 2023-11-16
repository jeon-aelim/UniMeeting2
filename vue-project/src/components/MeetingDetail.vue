<template>
    <main class="row">
        <!--        상단 왼쪽(카테고리, 제목, 신청현황)-->
        <div class="col mb-2">

            <!--            카테고리(button 클릭 시 해당 카테고리의 meeting board 페이지로 이동)-->
            <div class="row justify-content-between mb-4" style="margin:2px;">
                <a style="width:200px;" class="col-1 btn btn-outline-primary">{{category}}</a>
            </div>

            <!--            미팅 제목-->
            <h1 class="fw-bold mb-2" >{{ title }}</h1>

            <!--            미팅 현재인원/ 모집인원-->
            <div class="text-body-secondary mb-4">
                <p class="fs-6"
                    th:text="|현재인원 / 모집인원 : ${meeting_member} / ${meeting.recruits} | + ' | ' + | 시작일: ${#strings.substring(meeting.start_datetime, 0, 10)}|">
                </p>
            </div>
        </div>

        <!--        상단 오른쪽( 신청/스크랩 버튼 등 )-->
        <div class="col mb-2 position-relative" style="text-align: right;">

            <!--            th:unless=${isWriter} -> 작성자가 아닐 경우-->
            <div v-if="writer" class="row justify-content-end">

                <!--                and th:if=${apply} -> 해당 소모임에 참가 신청을 하지 않은 경우, 신청 버튼 보이기 -->
                <!--                and th:unless=${apply} -> 해당 소모임에 참가 신청을 한 경우, 신청 취소 버튼 보이기 -->
                <div class="col-auto offset-md-4" v-if="applicant">
                    <a class="btn btn-primary btn-lg px-4 btn-color"
                        th:href="@{/meeting/apply(meeting_idx=${meeting.idx})}">신청</a>
                </div>
                <div class="col-auto offset-md-4" v-if="!applicant">
                    <a class="btn btn-primary btn-lg px-4 btn-color"
                        th:href="@{/meeting/apply/cancel(meeting_idx=${meeting.idx})}">신청 취소</a>
                </div>

                <!--                and th:if=${scrap} -> 해당 소모임에 스크랩을 하지 않은 경우, 스크랩 버튼 보이기 -->
                <!--                and th:unless=${scrap} -> 해당 소모임에 스크랩을 한 경우, 스크랩 취소 버튼 보이기 -->
                <!-- <div class="col-auto" v-if="">
                    <a class="btn btn-primary btn-lg px-4 btn-color"
                        th:href="@{/meeting/scrap(meeting_idx=${meeting.idx})}">스크랩</a>
                </div>
                <div class="col-auto" th:unless="${scrap}">
                    <a class="btn btn-primary btn-lg px-4 btn-color"
                        th:href="@{/meeting/scrap/cancel(meeting_idx=${meeting.idx})}">스크랩 취소</a>
                </div> -->
            </div>

            <!--            th:unless=${isWriter} -> 작성자일 경우-->
            <div v-if="writer">
                <!--                삭제 & 수정 버튼 -->
                <div class="btn-group position-absolute top-0 end-0" role="group" aria-label="Basic mixed styles example"
                    style="width:max-content;">
                    <button type="button" class="btn btn-danger"
                        th:onclick="|location.href='@{/meeting/deleteMet(idx = ${meeting.idx}, writer_nickname = ${meeting.writer_nickname})}'|">삭제
                    </button>
                    <button type="button" class="btn btn-success" th:onclick="|location.href='@{/updateMetForm.html}'|">수정
                    </button>
                </div>
                <!--                신청 현황 offcanvas 열기 버튼-->
                <button id="currentApply" class="btn btn-primary position-absolute bottom-0 end-0 mb-4" type="button"
                    data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">신청
                    현황</button>
            </div>
        </div>
        <hr class="mb-5">

        <!--        하단(이미지, 글 내용) -->
        <div class="meeting-contents">

            <!--            th:if="${meeting_image}" -> 이미지가 포함된 글일 경우-->
            <div th:if="${meeting_image}" id="carouselExampleDark"
                class="carousel carousel-dark slidez border border-dark-subtle mb-5" data-bs-ride="carousel">
                <div class="carousel-inner position-absolute top-50 start-50 translate-middle">
                    <div class="carousel-item active" th:each="image, index: ${meeting_image}" th:if="${index.index} == 0">
                        <img th:src="${image}" class="d-block" alt="no image">
                    </div>
                    <div class="carousel-item" th:each="image, index: ${meeting_image}" th:unless="${index.index} == 0">
                        <img th:src="${image}" class="d-block" alt="no image">
                    </div>
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
            <div class="mb-5 fs-6" th:text="${meeting.content}" style="width:80%;"></div>
        </div>

    </main>
    <!--    신청 현황 offcanvas-->
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasRightLabel">신청 현황</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div th:if="${applicants}" class="offcanvas-body">
            <div th:each="ap:${applicants}" class="card mb-2" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title" th:text="${ap.nickname}"></h5>
                    <p class="card-text" th:text="${ap.category}"></p>
                    <a th:if="${ap.accepted} == 0"
                        th:href='@{/meeting/accept(meeting_idx=${ap.meeting_idx}, user_idx=${ap.user_idx})}'
                        class="btn btn-primary">수락</a>
                    <a th:if="${ap.accepted} == 0"
                        th:href='@{/meeting/accept/delete(meeting_idx=${ap.meeting_idx}, user_idx=${ap.user_idx})}'
                        class="btn btn-primary">거절</a>
                    <a th:if="${ap.accepted} == 1"
                        th:href='@{/meeting/accept/cancel(meeting_idx=${ap.meeting_idx}, user_idx=${ap.user_idx})}'
                        class="btn btn-primary">수락 취소</a>
                </div>
            </div>
        </div>
        <div th:unless="${applicants}" class="offcanvas-body">
            아직 신청한 사람이 없어요!
        </div>

    </div>
</template>
<script setup>
import { ref, onBeforeMount } from 'vue';
import { api } from '../../common'
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

const regex = /[^0-9]/g;
const url = location.pathname;
let meeting_idx;
if(url) {
    meeting_idx = url.replace(regex, "");

}
onBeforeMount(() => {
    api(`http://localhost:8090/meetings/${meeting_idx}`, "GET", {})
        .then((resp) => {
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
        })
})


</script>