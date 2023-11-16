import { defineStore } from 'pinia';
import {api} from '../../common'
import {ref} from 'vue';

export const useMeetingDetailStore = defineStore('meeting_detail', () => {
    const idx = ref(0);
    const title = ref('');
    const content = ref('');
    const category = ref('');
    const recruits = ref(0);
    const location = ref('');
    const startDatetime = ref('');
    const createdDatetime = ref('');
    const currentRecruits = ref(0);
    const images = ref([]);
    const applicant = ref(false);
    const writer = ref(false);

    const meeting = {};
    let getMeeting = (idx) => {
        api(`http://localhost:8090/meetings/${idx}`)
        .then((resp) => {
            meeting = resp;
            idx.value = resp.idx;
            title.value = resp.title;
            content.value = resp.content;
            category.value = resp.category;
            recruits.value = resp.recruits;
            location.value = resp.location;
            startDatetime.value = resp.startDatetime;
            createdDatetime.value = resp.createdDatetime;
            currentRecruits.value = resp.currentRecruits;
            images.value = resp.images;
            applicant.value = resp.applicant;
            writer.value = resp.writer;
        })
    } 

    return { idx, title, content, category, recruits, location,
            startDatetime, createdDatetime, currentRecruits, images,
            applicant, writer, getMeeting }
})

// {
//     "idx": 56,
//     "title": "어둠 속 공포: 새로운 공포 영화 소모임",
// 		"content": "..."
//     "category": "영화",
//     "recruits": 6,
//     "location": " 경기 성남시",
//     "startDatetime": "2023-11-18T00:00:00",
//     "createdDatetime": "2023-10-23T16:26:03",
//     "writerNickname": "도히",
//     "currentRecruits": 0,
//     "images": [
//         "/images/56/공포.jpg",
//         "/images/56/공포2.jpg"
//     ],
//     "applicant": false,
//     "writer": false
// }