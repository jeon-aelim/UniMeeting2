import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle.min.js"
import "./assets/css/mypage_user_edit.css"
import router from './router'
import VueAwesomePaginate from "vue-awesome-paginate";
import "vue-awesome-paginate/dist/style.css";

createApp(App).use(router).use(createPinia()).mount('#app')
