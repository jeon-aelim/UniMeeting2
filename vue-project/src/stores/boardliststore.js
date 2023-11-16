// boardliststore.js
import { defineStore } from 'pinia';
import axios from 'axios';

export const useBoardListStore = defineStore('boardList', {
  state: () => ({
    boards: [], // 게시판 정보를 저장할 변수
    currentBoard:{},
  }),
  actions: {
    // 특정 타입의 게시판 정보를 가져오는 액션
    async fetchBoardListByType(boardType) {
      try {
        // Axios를 사용하여 주어진 URL에서 게시판 정보를 가져옴.
        const response = await axios.get(`http://localhost:8090/boards/type/${boardType}`);

        // 가져온 정보를 boards 변수에 저장합니다.
        this.boards = response.data;
        console.log('게시판 정보', response.data);
      } catch (error) {
        // 오류가 발생하면 콘솔에 오류 메시지를 출력합니다.
        console.error('게시판 정보를 가져오는 동안 오류 발생:', error);
      }
    },

    async fetchBoardDetail(boardIdx) {
        try {
          // Axios를 사용하여 특정 게시글의 상세 정보를 가져옴.
          const response = await axios.get(`http://localhost:8090/boards/${boardIdx}`);
  
          this.currentBoard = response.data;
          console.log('게시글 상세 정보', response.data);
        } catch (error) {
          console.error('게시글 상세 정보를 가져오는 동안 오류 발생:', error);
        }
      },


  },
});
