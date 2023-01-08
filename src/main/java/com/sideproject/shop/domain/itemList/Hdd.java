package com.sideproject.shop.domain.itemList;

import com.sideproject.shop.domain.Item;

import javax.persistence.Entity;


@Entity
public class Hdd extends Item {

    private String capacity;
    private String transferSpeed;
    private String bufferCapacity;

}
