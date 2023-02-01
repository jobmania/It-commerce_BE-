package com.sideproject.shop.domain.itemList;

import com.sideproject.shop.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@Getter
@NoArgsConstructor
public class Cpu extends Item {

    private String coreCount;  // 코어수
    private String threadCount; // 스레드 수
    private String normalClock; // 기본 클럭
    private String maxClock; // 최대 클럭
    private String ddr; // cpu 메모리 규격


    public Cpu(String itemName, String price) {
        this.itemName = itemName;
        this.price = price;
    }
}
