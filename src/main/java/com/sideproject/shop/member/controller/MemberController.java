package com.sideproject.shop.member.controller;

import com.sideproject.shop.jwt.TokenProvider;
import com.sideproject.shop.member.entity.dto.MemberRequestDto;
import com.sideproject.shop.member.entity.dto.MemberResponseDto;
import com.sideproject.shop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    @PostMapping("/auth/signup")  // 회원가입
    public ResponseEntity<MemberResponseDto> signUpMember(@RequestBody MemberRequestDto memberRequestDto){

        return ResponseEntity.ok(memberService.signUpMember(memberRequestDto)); // 임ㅅ디로
    }

    @GetMapping("/checking")
    public ResponseEntity<String> checkMemberIdDuplication(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/login")
    public ResponseEntity<String> signInMember(){
        return ResponseEntity.ok("hello");
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> signOutMember(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/reissue")
    public ResponseEntity<String> reissueMemberToken(){
        return ResponseEntity.ok("hello");
    }



}
