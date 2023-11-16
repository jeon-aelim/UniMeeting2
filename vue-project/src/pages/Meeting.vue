<template>
    모임 페이지
    <div class="page-container">
        <Side @chage-category="chageCategory"></Side>

        <div class="contents-right">
            <div class="meeting-search">
                <form id="search" method="get" th:action="@{/meeting{ctgr}(ctgr=${path_ctgr})}">
                    <div class="search-label">
                        <label><input id="search-submit" type="image" src="src/assets/images/icon-search.png" alt="검색"></label>
                        <label><input id="search-text" name="search" required /><br></label>
                    </div>
                    <!--                <input type="submit" value="검색" />-->
                </form>
                <!-- Insert Meeting Button -->
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

    let category = ref("")
    function chageCategory(data){
        category.value = data
    }

    const getCategory = computed(() => category.value);

</script>

<style>
    @import '@/assets/css/meeting.css';
    @import '@/assets/css/meetingBox.css';
    .page-container {
        display: flex;
        flex-direction: row;
        justify-content: center;
        /* background-color: aquamarine; */
        position: relative;
        /* width: 1440px;
        height: 880px; */
    }
</style>
