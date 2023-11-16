<template>
    <div class="contents-right">
        <div id="info_result"></div>
    </div>
</template>

<script setup>
    import { defineProps, watch, computed, reactive  } from 'vue'; 
    
    import { api } from '@/public/common';
    import { makeMeetingBlock } from '@/public/meetingBlock'

    let url = "http://localhost:8090/mypage/meetings/";
    let meetings = reactive([]);
    api(url + "participated", "get").then(data => {
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
    function getMeeting(s, url) {
        url = "http://localhost:8090/mypage/meetings/";
        cleardiv()
        switch(s){
            case "attend":
                url += "participated";
                break
            case "create":
                url += "created";
                break
            case "scrap":
                url += "scraped"
                break
        }
        api(url, "get").then(meetings => {
            for(let o of meetings){
                makeMeetingBlock(o);
            }
        })
        // console.log(url);
    }

    function cleardiv() {
        let myDiv = document.getElementById("info_result");
        // console.log(myDiv);
        myDiv.innerHTML = "";
    }
</script>

<style scoped>
@import '@/assets/css/meetingBox.css';
@import '@/assets/css/mypage.css';
* {
    margin: 5px;
}
</style>