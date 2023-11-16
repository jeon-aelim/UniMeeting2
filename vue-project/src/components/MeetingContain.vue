<template>
    <div id="info_result"></div>
</template>

<script setup>
    import { defineProps, watch, computed, reactive  } from 'vue'; 
    import { api } from '@/public/common';
    import { makeMeetingBlock } from '@/public/meetingBlock';

    const p = defineProps({
        category : String
    });

    let url = "http://localhost:8090/meetings";
    let obj = reactive({ })
    const getCategory = computed(() => p.category);
    watch(getCategory, (cur, prev) => {
        getMeeting(cur, url)
      }
    )

    function getMeeting(ctgr, url) {
        url = "http://localhost:8090/meetings";
        cleardiv()
        if(ctgr != "")
            url += `?ctgr=${ctgr}`

        api(url, "get").then(meetings => {
            console.log(meetings)
            for(let o of meetings){
                makeMeetingBlock(o);
            }
        })
        // console.log(url);
    }

    function cleardiv() {
        let myDiv = document.getElementById("info_result");
        console.log(myDiv);
        myDiv.innerHTML = "";
    }
</script>

<style scoped>
</style>