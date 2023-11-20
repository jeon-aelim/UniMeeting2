<template>
    <div class="contents-right">
        <div id="info_result"></div>
        <component :is="currentComponent" :userObj="user[0]" v-if="currentComponent"></component>
    </div>
</template>

<script setup>
    import { defineProps, watch, computed, reactive  } from 'vue'; 
    
    import { api, cleardiv } from '@/public/common';
    import { makeMeetingBlock, makeMyinfoBlock, makeWithDraw } from '@/public/makeBlock'
    import MyInfo from '@/components/MypageMyInfo.vue';
    import Withdraw from '@/components/MypageWithDraw.vue';

    let url = "http://localhost:8090/mypage/meetings";
    let meetings = reactive([]);
    api(url + "/participated", "get").then(data => {
        for(let o of data){
            makeMeetingBlock(o);
        }
    });   
    const p = defineProps({
        state : String
    });

    const getState = computed(() => p.state);
    watch(getState, (cur, prev) => {
        cleardiv()
        getMeeting(cur, url)
      }
    )

    let user = []
    api("http://localhost:8090/user/minjae", "get", {}).then(data => {user.push(data)})

    let currentComponent = null;
    function getMeeting(s, url) {
        url = "http://localhost:8090/mypage/meetings";
        let flag = true
        currentComponent = null;
        cleardiv()
        switch(s){
            case "attend":
                url += "/participated";
                break;
            case "create":
                url += "/created";
                break;
            case "scrap":
                url += "/scraped"
                break;
            case "myInfo":
                flag = false;
                currentComponent = MyInfo;
                break;
            case "withDraw":
                currentComponent = Withdraw;
                flag = false;
        }

        if(flag) {
            api(url, "get").then(meetings => {
                for(let o of meetings){
                    makeMeetingBlock(o);
                }
            })
        } else {

        }
    } 
</script>

<style scoped>
@import '@/assets/css/meetingBox.css';
@import '@/assets/css/mypage.css';
* {
    margin: 5px;
}
</style>