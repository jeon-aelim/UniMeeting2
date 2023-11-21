<template>
    <h1 style="font-size:40px">UniMeeting 커뮤니티에서 자유롭게 이야기 나눠보세요!</h1>
    <div class="inner">
        <form method = "get" action ="/search" class="search">
            <input type = "search" name = "keyword" class="search-bar" placeholder="검색어를 입력해 주세요">
            <input type = "image" v-bind:src="myFile" value = "검색" class="searching">
        </form>
    </div>
    <div class="list_container">
        <div class="form-select1">
        <select name="changeTest" class="form-select form-select-sm" aria-label=".form-select-sm example">
            <option selected>menu</option>
            <option value="no">기본</option>
            <option value="popular">인기순</option>
            <option value="title">제목순</option>
            <option value="start">최신순</option>
        </select>
        </div>
            <div class="row row-cols-3 row-cols-md-3 g-4" th:if="${ list }" th:each="st : ${list}">
                <div class="col">
                    <div class="card">
                        <a th:href="@{/meeting/post(meeting_idx=${st.idx})}">
                            <img th:src="${st.image_url}" onerror="this.src='/images/book.png';" class="card-img-top">
                            <div class="card-body">
                                <h4 class="card-title" style="font-weight: bold">[[${st.title}]]</h4>
                                <p class="card-text">[[${st.content_text}]]</p>

                            </div>
                            <div class="card-date" style="color: gray" th:data-created_datetime="${st.created_datetime}"></div>
                        </a>

                    </div>
                </div>
            </div>
        
    </div>

</template>

<script>
// import EduComp1 from '@/components/EduComp1.vue'
// import  { ref } from Vue;
// export default {
//     setup(){
//         const myFile = ref('/images/search_icon.png') 
    
//         return {
//             myFile,
//         }
//     }    
// }
</script>

<style scoped>
@import '@/assets/css/main.css';

</style>