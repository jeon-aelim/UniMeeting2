<style>
@import '@/assets/css/mypage.css';
* {
    margin: 5px;
}
</style>
<template>
    <Side @chage-state="chageState"></Side>
    <div>
        <h1>
        dafsdfasdf {{ state }}
        </h1>
    </div>
    
</template>

<script setup>
    import { ref, reactive } from 'vue'
    import Side from '@/components/MypageSide.vue'
    
    let state = ref("attend")

    function chageState(data){
        state.value = data
        console.log(state.value)
    }

</script>

