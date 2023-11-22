<template>
{{title}}
    <h1 style="font-size:40px">UniMeeting 커뮤니티에서 자유롭게 이야기 나눠보세요!</h1>
    <div class="inner">
        <form id="search" @submit.prevent="meetingForm" class="search">
            <input type = "search"  class="search-bar" placeholder="검색어를 입력해 주세요">
            <input type = "image" :src="icon" value = "검색" class="searching">
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
            <div class="row row-cols-3 row-cols-md-3 g-4" >
                <div class="col" v-for="meeting in meetings.values">
                    <div class="card">
                        <a th:href="@{/meeting/post(meeting_idx=${st.idx})}">
                            <img src="{meeting.imageUrl}" 
                            @error="this.src='/images/book.png';" class="card-img-top">
                            <div class="card-body">
                                <h4 class="card-title" style="font-weight: bold">{{ meeting.title }}</h4>
                                <p class="card-text">{{ meeting.content }}</p>

                            </div>
                            <div class="card-date" style="color: gray" th:data-created_datetime="${st.created_datetime}"></div>
                        </a>

                    </div>
                </div>
            </div>
        
    </div>

</template>

<script setup>
    import { api, cleardiv } from '@/public/common';
    import { onBeforeMount, reactive, ref } from 'vue';
    import  icon from "../assets/images/search_icon.png";
    // import { useBoardListStore } from '@/stores/boardliststore.js'
    // import NoticeList from '@/components/NoticeList.vue';
    // let url = ref("http://localhost:8090/meetings");
    
    // const meeting = reactive({
    //     title: "",
    //     content: "",
    // });
    const meetings = reactive([]);

    const url1 = () => {
    }

    onBeforeMount( () => {
        api("http://localhost:8090/meetings", "get", {})
            .then(resp => {
                meetings.values = resp;
                console.log(meetings)

        // for(let o of meetings){
        //     makeMeetingBlock(o);
        // }
            })
    }

    )
    
    // const temp = reactive("meeting.title");


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