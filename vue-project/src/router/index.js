import { createRouter, createWebHistory } from 'vue-router'

import Home from '@/pages/MainRounge.vue'
import meeting from '@/pages/Meeting.vue'
import board from '@/pages/Board.vue'
import notice from '@/pages/Notice.vue'
import login from '@/pages/Login.vue'
import join from '@/pages/CreateAccount.vue'
import mypage from '@/pages/Mypage.vue'
import meetingView from '@/pages/MeetingView.vue'

const router = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/', component: Home },
        { path: '/meetings', component: meeting },
        { path: '/board', component: board },
        { path: '/notice', component: notice },
        { path: '/login', component: login },
        { path: '/join', component: join },
        { path: '/mypage', component: mypage},
        { path: '/meeting/:idx', component: meetingView}
    ]
})

export default router;