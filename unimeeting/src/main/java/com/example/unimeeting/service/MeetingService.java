package com.example.unimeeting.service;


import com.example.unimeeting.domain.Meeting;
import com.example.unimeeting.domain.MeetingImage;
import com.example.unimeeting.domain.Member;
import com.example.unimeeting.domain.User;
import com.example.unimeeting.dto.*;
import com.example.unimeeting.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingImageRepository  meetingImageRepository;
    private final MemberRepository memberRepository;
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;

    public List<String> findCatogory(){
        return meetingRepository.findDistinctCategoryBy();
    }

    public Meeting findById(Integer id){
        return meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public MeetingResponse getMeetingOne(Integer id, User user){
        MeetingResponse ms = new MeetingResponse(meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id)),
                memberRepository.countByMeetingIdx(id),
                meetingImageRepository.findImageUrlByMeetingIdx(id),
                meetingRepository.existsByIdxAndUserNickname(id, user.getNickname()),
                memberRepository.existsByMeetingIdxAndUserIdx(id, user.getIdx()),
                scrapRepository.existsByMeetingIdxAndUserIdx(id, user.getIdx()));
        return ms;
    }

    public List<MeetingWithDetailsDTO> getAllMeeting(String search){
        List<MeetingWithDetailsDTO> list = new ArrayList<>();
        meetingRepository.findAllByTitleContainingOrContentContaining(search,search)
                .forEach(element -> list.add(new MeetingWithDetailsDTO(element,
                        memberRepository.countByMeetingIdx(element.getIdx()),
                        meetingImageRepository.findImageUrlByMeetingIdx(element.getIdx()).isEmpty() ?
                                "" :meetingImageRepository.findImageUrlByMeetingIdx(element.getIdx()).get(0))
                ));

//        List<MeetingWithDetailsDTO> list = (List<MeetingWithDetailsDTO>) meetingrepository.findAllByTitleContainingOrContentContaining(search,search)
//                .stream().map(e ->
//                        new MeetingWithDetailsDTO(e,
//                                memberRepository.countByMeetingIdx(e.getIdx()),
//                                meetingImageRepository.findImageUrlByMeetingIdx(e.getIdx()).get(0)));

        return list;
    }

    public List<MeetingWithDetailsDTO> getMeetingByCtgr(String category, String search){
        List<MeetingWithDetailsDTO> list = new ArrayList<>();
        meetingRepository.searchMeetingInCategory(category, search,search)
                .forEach(element -> list.add(new MeetingWithDetailsDTO(element,
                        memberRepository.countByMeetingIdx(element.getIdx()),
                        meetingImageRepository.findImageUrlByMeetingIdx(element.getIdx()).isEmpty() ?
                                "" :meetingImageRepository.findImageUrlByMeetingIdx(element.getIdx()).get(0))
                ));
        return list;
    }

    public Boolean addMeeting(AddMeetingRequest request, User user, List<MultipartFile> mreq){
        try{
            Meeting meeting = meetingRepository.save(request.toEntity(user));
            int meeting_idx = meeting.getIdx();
            List<MultipartFile> list = mreq;
            if(!list.isEmpty()){

                String path = "/images/" + meeting_idx;
                // 상대 경로를 찾는 함수인 getRealPath()는 프로젝트 폴더 구조에서 resources가 아닌 webapp 폴더를 우선으로 찾고
                //  해당 폴더가 존재하지 않으면 위와 같이 임시 폴더를 찾아간다.
                // webapp 폴더를 만드는 방법도 있으나, Spring Boot는 jar로 배포되기 때문에 webapp 폴더를 만든다면 정상 배포 되지 않는다.
                String realPath = "C:/UniMeetingFile" + path;
                File isDir = new File(realPath);
                if (!isDir.isDirectory()) {
                    isDir.mkdirs();
                }

                for (MultipartFile mfile : list) {
                    // replace -> 파일 이름의 공백을 언더바로 변경
                    String fileName = mfile.getOriginalFilename().replace(" ", "_");
                    System.out.println(fileName);

                    try {
                        File f = new File(realPath + "/"+ fileName);
                        if (f.exists()) {
                            System.out.println("already exist");
                        } else {
                            mfile.transferTo(f);
                            MeetingImage meetingImage = MeetingImage.builder()
                                    .meetingIdx(meeting_idx)
                                    .imageUrl(path+"/"+fileName)
                                    .build();
                            meetingImageRepository.save(meetingImage);
                            System.out.println("upload images success");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("upload images error");
                    }
                }
            }

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Meeting updateMeeting(Integer id, UpdateMeetingRequest update){
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        meeting.update(update.getTitle(),
                       update.getContent(), update.getCategory(),
                       update.getRecruits(), update.getLocation(), update.getStartDatetime());

        return meeting;
    }

    public boolean deleteMeeting(Integer id){
        boolean isSuccess = false;
        if (meetingRepository.findById(id).isPresent()) {
            meetingRepository.deleteById(id);
            isSuccess = true;
        }
        return isSuccess;
    }

    public Member addMember(AddMemberRequest addMemberRequest,Integer user_idx){
        User user = userRepository.findById(user_idx)
                .orElseThrow(()-> new IllegalArgumentException());

        addMemberRequest.setUser(user);
        return memberRepository.save(addMemberRequest.toEntity());
    }

    public boolean updateMember(Integer id){

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        boolean isSuccess = false;

        if (memberRepository.findById(id).isPresent()) {
            member.update();
            isSuccess = true;
        }
        return isSuccess;
    }

    public boolean deleteMember(Integer id){
        boolean isSuccess = false;
        if (memberRepository.findById(id).isPresent()) {
            memberRepository.deleteById(id);
            isSuccess = true;
        }
        return isSuccess;
    }

    public List<MemberResponse> getMember(Integer id){
        List<MemberResponse> list = new ArrayList<>();
        memberRepository.findByMeetingIdx(id)
                .forEach(member ->  list.add(new MemberResponse(member)));
        return list;
    }
}
