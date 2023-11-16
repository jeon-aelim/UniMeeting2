<template>
    모임 상세 페이지
    {{ $route.params }}
        <MeetingDetail class="col-lg-8 mx-auto p-5 py-md-5"></MeetingDetail>

</template>

<script setup>
import MeetingDetail from '@/components/MeetingDetail.vue';

    // console.log(window.location.href)
</script>
