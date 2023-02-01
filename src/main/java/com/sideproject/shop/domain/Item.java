package com.sideproject.shop.domain;



import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) // 조인 테이블 만들기 ..
@DiscriminatorColumn // 엔티티 명 넣어주기     // 단잁 테이블을 쓸거나,, 아니면 조인테이블을 사용할거냐.. //복잡하면 조인, 단순하면 단일..?
public class Item {
    @Id
    @GeneratedValue
    @JoinColumn(name = "ITEM_ID")
    protected Long id;

    protected String itemName;  // 상품명

    protected String price; // 가격


    @Enumerated(EnumType.STRING)
    protected ItemStatus itemCategory;  // 사무용, 작업용, 게임용

    protected String  siteName;  // 사이트 이름
    protected String siteAddress; // 사이트 주소
    protected String maker;  // 제조사

    protected LocalDateTime dateTime; // 등록날짜
    protected LocalDateTime updateTime ; // 갱신날짜

    @Transient
    protected Long viewCount;  // 조회수 구현



}
