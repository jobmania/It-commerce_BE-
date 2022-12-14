package com.sideproject.shop.domain;

import jakarta.persistence.*;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue
    @Column(name = "BOOKMARK_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMEBER_ID")
    private Member member;
}
