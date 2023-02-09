package com.sideproject.shop.member.service;

import com.sideproject.shop.ResponseDto;
import com.sideproject.shop.jwt.RefreshToken;
import com.sideproject.shop.jwt.TokenProvider;
import com.sideproject.shop.member.entity.Member;
import com.sideproject.shop.member.entity.dto.MemberRequestDto;
import com.sideproject.shop.member.entity.dto.MemberResponseDto;
import com.sideproject.shop.jwt.TokenDto;
import com.sideproject.shop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseDto<?> signUpMember(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getUser_id())) {
            return ResponseDto.fail("200","가입되어 있습니다.");
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        return ResponseDto.ok(MemberResponseDto.of(memberRepository.save(member)),"성공~");
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> checkMember(MemberRequestDto memberRequestDto){
        if (memberRepository.existsByEmail(memberRequestDto.getUser_id())) {
            return ResponseDto.fail("200","가입되어 있습니다.");
        }

        // 아이디, 비번 정규식 검증 로직을 따로 빼자.

        return ResponseDto.ok(" ","중복된 아이디가 아니다.");
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> findMember(MemberRequestDto memberRequestDto){
        Member member = memberRepository.findByEmail(memberRequestDto.getUser_id()).orElseThrow(
                () -> new RuntimeException("로그인 유저 정보가 없습니다."));
        return ResponseDto.ok(MemberResponseDto.of(member),"멤버 찾기 성공");
    }


    @Transactional(readOnly = true)
    public MemberResponseDto findMemberInfoByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("이메일관련 유저 정보가 없습니다."));
    }


    @Transactional
    public ResponseDto<?> loginMember(MemberRequestDto memberRequestDto, HttpServletResponse response ){

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(memberRequestDto.getUser_id(),memberRequestDto.getUser_pw());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(userToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.createToken(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

//        refreshTokenRepository.save(refreshToken);

        response.setHeader("Authorization","Bearer "+ tokenDto.getAccessToken());
        response.setHeader("Refresh_Token",tokenDto.getRefreshToken());

        // 리프레쉬를 레디스에서 보관한다면??

        return ResponseDto.ok(tokenDto,"로그인 성공.");
    }



}
