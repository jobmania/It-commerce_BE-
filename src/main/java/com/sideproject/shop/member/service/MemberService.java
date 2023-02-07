package com.sideproject.shop.member.service;

import com.sideproject.shop.member.entity.Member;
import com.sideproject.shop.member.entity.dto.MemberRequestDto;
import com.sideproject.shop.member.entity.dto.MemberResponseDto;
import com.sideproject.shop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MemberResponseDto findMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("로그인 유저 정보가 없습니다."));
        return MemberResponseDto.of(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMemberInfoByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("이메일관련 유저 정보가 없습니다."));
    }


    @Transactional
    public MemberResponseDto signUpMember(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getUser_id())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

}
