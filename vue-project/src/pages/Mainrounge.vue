<template>
    <h1 style="font-size:40px">UniMeeting 커뮤니티에서 자유롭게 이야기 나눠보세요!</h1>
    <div class="inner">
        <form id="search" @submit.prevent="meetingForm" class="search">
            <input type = "search" v-model = "keyword" class="search-bar" placeholder="검색어를 입력해 주세요">
            <input type = "image" :src="myFile" value = "검색" class="searching">
        </form>
    </div>
    <div class="list_container">
        <div class="form-select1">
        <select name="changeTest" class="form-select form-select-sm" aria-label=".form-select-sm example">
            <option selected>menu</option>
            <option value="no">기본</option>
            <option value="popular">인기순</option>
            <option value="title">제목순</option>
            <option value="start">최신순</option>
        </select>
        </div>
            <div class="row row-cols-3 row-cols-md-3 g-4" v-if="list && list.length > 0">
                <div class="col">
                    <div class="card">
                        <a th:href="@{/meeting/post(meeting_idx=${st.idx})}">
                            <img th:src="${st.image_url}" @error="this.src='/images/book.png';" class="card-img-top">
                            <div class="card-body">
                                <h4 class="card-title" style="font-weight: bold">[[${st.title}]]</h4>
                                <p class="card-text">[[${st.content_text}]]</p>

                            </div>
                            <div class="card-date" style="color: gray" th:data-created_datetime="${st.created_datetime}"></div>
                        </a>

                    </div>
                </div>
            </div>
        
    </div>

</template>

<script >
    import  { ref } from 'vue';
    // import { api, cleardiv } from '@/public/common';
    // import { useBoardListStore } from '@/stores/boardliststore.js'
    // import NoticeList from '@/components/NoticeList.vue';
    export default {
        setup(){
            const myFile = ref('./images/search_icon.png') 
    
            return {
                myFile,
            }
        }    
}

    // let url = ref("http://localhost:8090/meetings")

    // let search = "";

    // api(url.value, "get").then(meetings => {
    //     cleardiv()
    //     for(let o of meetings){
    //         makeMeetingBlock(o);
    //     }
    // })

    // const meetingForm = () => {
    //     cleardiv()
    //     api(url.value + `?search=${search}`, "get").then(meetings => {
    //         for(let o of meetings){
    //             makeMeetingBlock(o);
    //         }
    //     })
    // }
</script>

<style scoped>
@import '@/assets/css/main.css';

</style>