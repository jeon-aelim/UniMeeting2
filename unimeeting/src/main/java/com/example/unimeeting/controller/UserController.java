package com.example.unimeeting.controller;

import com.example.unimeeting.domain.User;
import com.example.unimeeting.repository.UserRepository;
import com.example.unimeeting.service.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserDetailService userDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;



    //아이디로 사용자 확인
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable String user_id) {
        Optional<User> user = userDetailService.findByUserId(user_id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //닉네임으로 사용자 확인
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<User> getUserByNickname(@PathVariable String nickname) {
        Optional<User> user = userDetailService.findByNickname(nickname);
        ResponseEntity<User> responseEntity;
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //이메일로 사용자 확인
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userDetailService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //===============로그인=================//
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestParam String user_id, @RequestParam String password) {
        if (userDetailService.findByUserIdAndPassword(user_id, password).isPresent()) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.badRequest().body("아이디 비밀번호를 잘못입력");
        }
    }
    //아이디로 검색
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String user_id, @RequestParam String nickname) {
        List<User> users = userDetailService.findByUserIdStartingWithOrNicknameStartingWith(user_id, nickname);
        return ResponseEntity.ok(users);
    }

    //===============회원가입=============//
    @PostMapping("/register")
    public ResponseEntity<String> register(User user) {
        try {
            userDetailService.register(user.getUserId(), user.getPassword(),user.getNickname(),user.getCategory(),user.getPhoneNumber(),user.getRole(),user.getEmail());
            return ResponseEntity.ok("성공적으로 회원 가입이 진행되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("join")
    public String join(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        System.out.println(user.getPassword());
        userRepository.save(user);
        return "회원 가입 완료";
    }
    @PostMapping("/login")
    public String login() {
        return "토큰 발행 완료";
    }
}