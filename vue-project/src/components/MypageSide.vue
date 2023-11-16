<template>
    <div class="nav-left">
        <div class=menu id="mypage_side">
            <div v-for="kind , idx in btn_kind" class="menu-item">
                <img class="menu-icon" :src="'src/assets/images/'+btn_images[idx]" alt="">
                <button @click="handleKind(kind)" class ="menu-item" :id="'side_'+kind" :name=kind> {{ btn_value[idx] }}</button>
            </div>
        </div>
  </div>
</template>

<script setup>
    import { defineEmits } from 'vue';
    const emit = defineEmits(["chage-state"]);

    const btn_kind = ["attend", "create", "scrap", "myInfo", "withDraw"];
    const btn_value = ["참여한 소모임", "생성한 소모임", "스크랩 소모임", "내 정보 수정", "회원 탈퇴"];
    const btn_images = ["home.png", "directory.png", "tag.png", "users.png", "users.png"];

    function handleKind(kind) {
        emit("chage-state", kind)
    }

</script>

<style scoped>
@import '@/assets/css/mypage.css';
* {
    margin: 5px;
}
</style>