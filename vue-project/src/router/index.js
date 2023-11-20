import { createRouter, createWebHistory } from 'vue-router'

import Home from '@/pages/MainRounge.vue'
import meeting from '@/pages/Meeting.vue'
import join from '@/pages/CreateAccount.vue'
import mypage from '@/pages/Mypage.vue'
import meetingView from '@/pages/MeetingView.vue'
import createMeeting from '@/pages/CreateMeeting.vue'
import updateMeeting from '@/pages/updateMeeting.vue'

import Free from '@/pages/Board/Free.vue'
import Notice from '@/pages/Board/Notice.vue'
import Detail from '@/pages/Board/Detail.vue'
import UserLogin from '@/pages/User/Login.vue'

const router = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/', component: Home },
        { path: '/meetings', component: meeting },
        { path: '/join', component: join },
        { path: '/mypage', component: mypage},
        { path: '/meeting/:idx', component: meetingView, props: true},
        { path: '/boards/type/free', component: Free },
        { path: '/boards/type/notice', component: Notice },
        { path: '/user/login', component:UserLogin},
        { path: '/meeting/goInsertMet', component: createMeeting },
        { path: '/meeting/goUpdateMet/:meeting_idx', component: updateMeeting },
        // { path: '/boards/type/notice', component: Notice },
        // { path: '/boards/:idx', component:BoardDetail},
    ]
})

export default router;