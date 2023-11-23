<template>
    <div class="page-container">
        <Side @chage-category="chageCategory"></Side>

        <div class="contents-right">
            <div class="meeting-search">
                <form id="search" @submit.prevent="meetingForm"> <!--url 동적 변경-->
                    <div class="search-label">
                        <label><input id="search-submit" type="image" src="src/assets/images/icon-search.png" alt="검색"></label>
                        <label><input id="search-text" v-model="search" required /><br></label>
                    </div>
                </form>
                <button class="make-meeting-button" onclick="location.href = '/meeting/goInsertMet'" >모임 만들기</button>
            </div>

            <Contain :category="getCategory"></Contain>
        </div>
    </div>
</template>

<script setup>
    import Side from '@/components/MeetingSide.vue';
    import Contain from '@/components/MeetingContain.vue';
    import { ref, computed } from 'vue';
    import { api, cleardiv } from '@/public/common';
    import { makeMeetingBlock } from '@/public/makeBlock';
    let category = ref("");
    let url = ref("http://localhost:8090/meetings");

    let search = "";

    api(url.value, "get").then(meetings => {
        cleardiv()
        for(let o of meetings){
            makeMeetingBlock(o);
        }
    })

    function chageCategory(data){
        category.value = data;
        url.value = `http://localhost:8090/meetings?ctgr=${category.value}`;
    }

    const meetingForm = () => {
        cleardiv()
        api(`${url.value}${category.value == "" ? '?' : '&'}search=${search}`, "get").then(meetings => {
            for(let o of meetings){
                makeMeetingBlock(o);
            }
        })
    }

    const getCategory = computed(() => category.value);
</script>

<style >
    @import '@/assets/css/meeting.css';
    @import '@/assets/css/meetingBox.css';
    .page-container {
        display: flex !important;
        flex-direction: row !important;
        justify-content: center !important;
        /* background-color: aquamarine; */
        /* position: relative; */
        /* width: 1440px;
        height: 880px; */
    }
</style>
