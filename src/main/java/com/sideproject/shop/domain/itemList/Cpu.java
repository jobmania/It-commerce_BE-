package com.sideproject.shop.domain.itemList;

import com.sideproject.shop.domain.Item;

import javax.persistence.Entity;


@Entity
public class Cpu extends Item {


    private String coreCount;  // 코어수
    private String threadCount; // 스레드 수
    private String normalClock; // 기본 클럭
    private String maxClock; // 최대 클럭
    private String ddr; // cpu 메모리 규격


    public String getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(String coreCount) {
        this.coreCount = coreCount;
    }

    public String getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(String threadCount) {
        this.threadCount = threadCount;
    }

    public String getNormalClock() {
        return normalClock;
    }

    public void setNormalClock(String normalClock) {
        this.normalClock = normalClock;
    }

    public String getMaxClock() {
        return maxClock;
    }

    public void setMaxClock(String maxClock) {
        this.maxClock = maxClock;
    }

    public String getDdr() {
        return ddr;
    }

    public void setDdr(String ddr) {
        this.ddr = ddr;
    }
}
