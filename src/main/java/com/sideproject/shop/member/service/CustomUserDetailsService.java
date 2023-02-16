
package com.sideproject.shop.member.service;

import com.sideproject.shop.member.entity.Member;
import com.sideproject.shop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자는 없습니다 이메일 : " + email));

        return new MemberDetailsImpl(member);
    }


//
//    @Override
//    @Transactional  // UserDetails 와 Authentication 의 패스워드를 비교하고 검증하는 로직
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return memberRepository.findByEmail(email)
//                .map(this::createMember)
//                .orElseThrow(() -> new UsernameNotFoundException(email + "데이터 베이스에 없음"));
//    }
//
//    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
//    private UserDetails createMember(Member member) {
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());
//
//        return new User(
//                String.valueOf(member.getId()),
//                member.getPassword(),
//                Collections.singleton(grantedAuthority)
//        );
//    }


}
