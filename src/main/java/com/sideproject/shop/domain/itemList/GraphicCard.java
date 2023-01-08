package com.sideproject.shop.domain.itemList;

import com.sideproject.shop.domain.Item;

import javax.persistence.Entity;


@Entity
public class GraphicCard extends Item {
    private String memoryClock;
    private String memoryCapacity;
    private String powerCapacity;
}