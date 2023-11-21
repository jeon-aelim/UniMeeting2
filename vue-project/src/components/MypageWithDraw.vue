<template>
    <div>
        <h3> 탈퇴 안내 </h3>

        <h5>사용하고 계신 아이디({{userObj.userId}})는 탈퇴할 경우 복구가 불가능합니다. </h5> <hr>
        <h5>탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다. </h5>
        <input type="hidden" name='user_pwd' id='user_pwd' :value="userObj.password">
        <input class='input_box input_chane_box' type='password' id='wd' v-model="inputpwd" @input="checkPWD" placeholder = '비밀번호 입력' required> <br>
        <button class='submit_btn' id='wdr-btn' :disabled="!showBtn" @click="withDrawPassword">
            <span id = 'submit_button'>회 원 탈 퇴</span>
        </button>
    </div>
    
</template>

<script setup>
    import { defineProps, ref } from 'vue'; 

    const p = defineProps({
        userObj : Object
    });

    let showBtn = ref(false)
    let inputpwd = ref("")

    const checkPWD = () => {
        showBtn.value = inputpwd.value === p.userObj.password;
    }

    function withDrawPassword() {
        console.log("wfff");
    }
</script>

<style>

</style>