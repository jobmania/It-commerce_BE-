package com.sideproject.shop.member.entity.dto;

import com.sideproject.shop.member.entity.Authority;
import com.sideproject.shop.member.entity.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private String user_id;
        private String user_pw;
        private String user_name;

        public Member toMember(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .email(user_id)
                    .password(passwordEncoder.encode(user_pw))
                    .authority(Authority.ROLE_USER)
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private String user_id;


        public static Response of(Member member) {
            return new Response(member.getEmail());
        }

    }
}
