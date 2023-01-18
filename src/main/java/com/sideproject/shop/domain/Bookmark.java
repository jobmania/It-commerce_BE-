package com.sideproject.shop.domain;


import com.sideproject.shop.domain.member.Member;

import javax.persistence.*;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue
    @Column(name = "BOOKMARK_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMEBER_ID")
    private Member member;
}
