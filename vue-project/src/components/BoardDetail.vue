<template>
    <div class="container mt-5">
      <h2 class="mt-3">{{ board.title }}</h2>
      <div id="content_box" class="mt-3">
        <p class="mt-4"></p>
        <p id="p_1" class="mt-4"><strong>작성자:</strong> <span>{{ board.writernickname }}</span></p>
        <p id="p_2" class="mt-4"><strong>작성일:</strong> <span>{{ board.createdDatetime }}</span></p>
        <p class="mt-4">{{ board.content}}</p>
      </div>
  
      <router-link class="btn btn-danger btn-delete" :to="{ path: `/board/delete/${board.idx}/${board.type}` }">삭제</router-link>
      <router-link class="btn btn-danger btn-delete" :to="{ path: `/updateWrite.html` }">수정</router-link>
      <router-link class="btn btn-danger btn-delete" :to="{ path: `/boards/${board.type}` }">목록</router-link>
    </div>
  </template>
  <script>
  import { useBoardListStore } from '@/stores/boardliststore.js'; 
  
  export default {
    data() {
      return {
        board:{},
      };
    },
    computed: {
    boardIdx() {
      // 라우터에서 전달된 게시글 idx
      return this.$route.params.idx;
    },
  },
    methods: {
      // 게시판 글 목록을 가져오는 메서드를 호출합니다.
      async fetchBoardList() {
        const boardListStore = useBoardListStore();
        await boardListStore.fetchBoardDetail(this.boardIdx);
      },
    },
    async mounted() {
    // 게시글 목록 스토어를 가져옵니다.
    const boardListStore = useBoardListStore();

    // 선택된 게시글의 상세 정보를 가져옵니다.
    this.board = await boardListStore.fetchBoardDetail(this.boardIdx);
  },
};
  </script>
  
  <style>
  /* 원하는 스타일링을 추가할 수 있습니다. */
  </style>