import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import "bootstrap/dist/css/bootstrap.min.css"
import "./assets/css/mypage_user_edit.css"
import router from './router'

createApp(App).use(router).use(createPinia()).mount('#app')
