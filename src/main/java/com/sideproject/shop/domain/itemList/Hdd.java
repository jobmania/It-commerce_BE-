package com.sideproject.shop.domain.itemList;

import com.sideproject.shop.domain.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Hdd extends Item {

    private String capacity;
    private String transferSpeed;
    private String bufferCapacity;

}
