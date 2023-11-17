import { createRouter, createWebHistory } from 'vue-router'

import Home from '@/pages/MainRounge.vue'
import meeting from '@/pages/Meeting.vue'
import login from '@/pages/Login.vue'
import join from '@/pages/CreateAccount.vue'
import mypage from '@/pages/Mypage.vue'
import meetingView from '@/pages/MeetingView.vue'
import createMeeting from '@/pages/createMeeting.vue'

import Free from '@/pages/Board/Free.vue'
import Notice from '@/pages/Board/Notice.vue'
import Detail from '@/pages/Board/Detail.vue'

const router = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/', component: Home },
        { path: '/meetings', component: meeting },
        { path: '/login', component: login },
        { path: '/join', component: join },
        { path: '/mypage', component: mypage},
        { path: '/meeting/:idx', component: meetingView},
        { path: '/boards/type/free', component: Free },
        { path: '/boards/type/notice', component: Notice },
        { path: '/meeting/upload', component: createMeeting },
        // { path: '/boards/type/notice', component: Notice },
        // { path: '/boards/:idx', component:BoardDetail},
    ]
})

export default router;