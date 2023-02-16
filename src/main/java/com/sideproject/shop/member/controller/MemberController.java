package com.sideproject.shop.member.controller;

import com.sideproject.shop.jwt.TokenProvider;
import com.sideproject.shop.member.entity.dto.MemberDto;
import com.sideproject.shop.member.entity.dto.MemberRequestDto;
import com.sideproject.shop.member.entity.dto.MemberResponseDto;
import com.sideproject.shop.member.service.MemberDetailsImpl;
import com.sideproject.shop.member.service.MemberService;
import com.sideproject.shop.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/signup")  // 회원가입 , 이너클래스 사용
    public ResponseEntity<Object> signUpMember(@RequestBody MemberDto.Request memberDto){
        return ResponseEntity.ok(memberService.signUpMember(memberDto)); //
    }

    @GetMapping("/auth/checking") // 아이디 중복검사
    public ResponseEntity<?> checkMemberIdDuplication(@RequestBody MemberRequestDto memberRequestDto){
        return ResponseEntity.ok(memberService.checkMember(memberRequestDto));
    }

    @GetMapping("/auth/findid")
    public ResponseEntity<?> findMemberInfoById() {
        return ResponseEntity.ok(memberService.findMemberInfoById(SecurityUtil.getCurrentMemberId()));
    }


    @PostMapping("/auth/login") // 로그인
    public ResponseEntity<Object> signInMember(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response

    ){

        return ResponseEntity.ok(memberService.loginMember(memberRequestDto,response));
    }
  // @AuthenticationPrincipal UserDetailsImpl userDetails
    @DeleteMapping("/logout") // 로그아웃
    public ResponseEntity<?> signOutMember(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/reissue") // 재발급.
    public ResponseEntity<?> reissueMemberToken(){
        return ResponseEntity.ok("hello");
    }



}
