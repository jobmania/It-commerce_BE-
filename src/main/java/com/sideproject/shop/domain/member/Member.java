package com.sideproject.shop.domain.member;

import com.sideproject.shop.domain.Bookmark;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private Long memberName;

    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarks  = new ArrayList<>();;
}
