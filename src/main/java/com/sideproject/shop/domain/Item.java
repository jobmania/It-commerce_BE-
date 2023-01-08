package com.sideproject.shop.domain;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 조인 테이블 만들기 ..
@DiscriminatorColumn // 엔티티 명 넣어주기     // 단잁 테이블을 쓸거나,, 아니면 조인테이블을 사용할거냐.. //복잡하면 조인, 단순하면 단일..?
public class Item {
    @Id
    @GeneratedValue
    @JoinColumn(name = "ITEM_ID")
    private Long id;

    private String itemName;  // 상품명

    private Long price; // 가격


    @Enumerated(EnumType.STRING)
    private ItemStatus itemCategory;  // 사무용, 작업용, 게임용

    private String  siteName;  // 사이트 이름
    private String siteAddress; // 사이트 주소
    private String maker;  // 제조사

    private LocalDateTime dateTime; // 등록날짜
    private LocalDateTime updateTime ; // 갱신날짜

    @Transient
    private Long viewCount;  // 조회수 구현


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ItemStatus getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemStatus itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
}
