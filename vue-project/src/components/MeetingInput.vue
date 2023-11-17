<template>
    <form id="form" @submit.prevent="submitMeeting()" method="post" enctype="multipart/form-data"
        class="col-lg-8 mx-auto p-4 py-md-5">
        <fieldset class="row mb-3">
            <legend class="col-form-label col-sm-2 pt-0">카테고리</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input class="form-check-input" type="radio" v-model="addMeeting.category" id="gridRadios1" value="운동" required>
                    <label class="form-check-label" for="gridRadios1">
                        운동
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" v-model="addMeeting.category" id="gridRadios2" value="게임">
                    <label class="form-check-label" for="gridRadios2">
                        게임
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" v-model="addMeeting.category" id="gridRadios3" value="애니">
                    <label class="form-check-label" for="gridRadios3">
                        애니
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" v-model="addMeeting.category" id="gridRadios4" value="영화">
                    <label class="form-check-label" for="gridRadios4">
                        영화
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" v-model="addMeeting.category" id="gridRadios5" value="요리">
                    <label class="form-check-label" for="gridRadios5">
                        요리
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" v-model="addMeeting.category" id="gridRadios6" value="코딩">
                    <label class="form-check-label" for="gridRadios6">
                        코딩
                    </label>
                </div>
            </div>
        </fieldset>
        <div class="row mb-3 position-relative">
            <label for="title" class="col-sm-2 form-label col-form-label">제목</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" v-model="addMeeting.title" id="title" minlength="2" required>
            </div>
        </div>
        <div class="row mb-3 position-relative">
            <label for="location" class="col-sm-2 form-label col-form-label">장소</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" v-model="addMeeting.location" id="location" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="recruits" class="col-sm-2 col-form-label">모집 인원</label>
            <div class="col-sm-10">
                <input type="number" min="2" v-model="addMeeting.recruits" class="form-control" id="recruits" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="startDatetime" class="col-sm-2 col-form-label">시작일</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" v-model="addMeeting.startDatetime" id="startDatetime" required>
            </div>
            <!--        <div class="invalid-feedback">-->
            <!--            Please enter a message in the textarea.-->
            <!--        </div>-->
        </div>
        <div class="row mb-3">
            <label for="content" class="form-label">소개</label>
            <textarea class="form-control" id="content" v-model="addMeeting.content" rows="10" maxlength="1000"
                required></textarea> {{ addMeeting.content.length }} / 1000
            <!--        <div class="invalid-feedback">-->
            <!--            Please enter a message in the textarea.-->
            <!--        </div>-->
        </div>
        <div class="mb-3">
            <label for="images" class="form-label">사진</label>
            <input class="form-control" type="file" id="images" @change="handleFileChange" multiple>
        </div>

        <button id="submit" type="submit" class="btn btn-primary"
            @click="was_validated()">등록</button><!--    제목: <label><input id="title" name="title" /><br></label>-->
        <!--    모집 인원: <label><input name="recruits" type="number" min="2"/><br></label>-->
        <!--    시작일: <label><input name="startDatetime" type="date"/><br></label>-->
        <!--    소개: <label><input name="content_text" maxlength="500" /><br></label>-->
        <!--    사진: <label><input type="file" name="images" multiple/><br></label>-->
    </form>
</template> 

<script setup>
import {reactive} from 'vue';
import {api} from '@/public/common'
import axios from 'axios';
const addMeeting = reactive({
    category:'',
    title:'',
    location:'',
    recruits:0,
    startDatetime:'',
    content:''
});
const images = reactive([]);

const submitMeeting = () => {
    let formData = new FormData();
    
    // 모든 선택된 파일을 FormData에 추가
    images.values.forEach((file, index) => {
        formData.append(index, file);
    });

    const formattedData = {
        ...addMeeting,
        startDatetime: addMeeting.startDatetime + 'T00:00', // Add a default time, adjust as needed
        images: formData
    };
    axios({
        method: "POST",
        url:"http://localhost:8090/meetings",
        data : formattedData,
        contentType: "multipart/form-data"
    }).then((resp) => {
        window.alert(resp.message);
    })
    .catch((e) => console.log(e))
}

const handleFileChange = (event) => {
    images.values = Array.from(event.target.files);
}


// 빈칸 입력 막기
    const was_validated = () => {
        const form = document.querySelector("#form");
        form.classList.add("was-validated");
    };


</script>

<style scoped>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}

* {
    font-family: 'Pretendard-Regular';

}

#title {
    width: 80%;

}

#recruits {
    width: 10%;
}

#startDatetime {
    width: 20%;
}

#images {
    width: 20%;
}

@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}

* {
    font-family: 'Pretendard-Regular';

}

.form-check-input:checked {
    background-color: #5302FE;
    border-color: #5302FE;
}

.btn-primary {
    --bs-btn-color: #fff;
    --bs-btn-bg: #5302FE;
    --bs-btn-border-color: #5302FE;
    --bs-btn-hover-color: #fff;
    --bs-btn-hover-bg: #6129FF;
    --bs-btn-hover-border-color: #6129FF;
    --bs-btn-focus-shadow-rgb: 49, 132, 253;
    --bs-btn-active-color: #fff;
    --bs-btn-active-bg: #6129FF;
    --bs-btn-active-border-color: #6129FF;
    --bs-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
    --bs-btn-disabled-color: #fff;
    --bs-btn-disabled-bg: #5302FE;
    --bs-btn-disabled-border-color: #5302FE;
}


.btn-outline-primary {
    --bs-btn-color: #0d6efd;
    --bs-btn-border-color: #0d6efd;
    --bs-btn-hover-color: #fff;
    --bs-btn-hover-bg: #dee2e6 !important;
    --bs-btn-hover-border-color: #0d6efd;
    --bs-btn-focus-shadow-rgb: 13, 110, 253;
    --bs-btn-active-color: #fff;
    --bs-btn-active-bg: #0d6efd;
    --bs-btn-active-border-color: #0d6efd;
    --bs-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
    --bs-btn-disabled-color: #0d6efd;
    --bs-btn-disabled-bg: transparent;
    --bs-btn-disabled-border-color: #0d6efd;
    --bs-gradient: none;
}

#title {
    width: 80%;

}

#recruits {
    width: 20%;
}

#startDatetime {
    width: 20%;
}

#images {
    border: 1px solid #dee2e6;
    width: 20%;
    background-image: none;
}

.form-check-label {
    color: #212529 !important;
}


form.was-validated .invalid-feedback {
    display: inline;
}

#displayNone * {
    display: none;
}
</style>