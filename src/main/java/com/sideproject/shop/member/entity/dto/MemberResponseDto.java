package com.sideproject.shop.member.entity.dto;

import com.sideproject.shop.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String user_id;


    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail());
    }

}
