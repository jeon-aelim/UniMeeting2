<template>
    <div class="nav-left">
        <div class=menu id="mypage_side">
            <!-- <div class="menu-item">
                <img class="menu-icon" src="@/assets/images/home.png" alt="">
                <button class ="menu-item" id="side_attend" name = "attend">참여 내역</button>
            </div>
            <div class="menu-item">
                <img class="menu-icon" src="@/assets/images/directory.png" alt="">
                <button class ="menu-item" id="side_create" name="create">생성한 소모임</button>
            </div>
            <div class="menu-item"> 
                <img class="menu-icon" src="@/assets/images/tag.png" alt="">
                <button class ="menu-item" id="side_scrap" name="scrap">스크랩 소모임</button>
            </div>
            <div class="menu-item">
                <img class="menu-icon" src="@/assets/images/users.png" alt="">
                <button class ="menu-item" id="side_myInfo" name="myInfo">내 정보 수정</button>
            </div>
            <div class="menu-item">
                <img class="menu-icon" src="@/assets/images/users.png" alt="">
                <button class ="menu-item" id="side_withdraw" name="withDraw">회원 탈퇴</button>
            </div> -->
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