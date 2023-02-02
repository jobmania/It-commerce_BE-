package com.sideproject.shop.crawling;

import com.sideproject.shop.domain.itemList.Cpu;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class Shop_Coupang {
    public static void main(String[] args) {
        //세션시작
        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
//        options.addArguments("headless");   //  실행시 띄우는 창
        options.addArguments("--disable-popup-blocking"); // 팝업 무시

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); //Normal: 로드 이벤트 실행이 반환 될 때 까지 기다린다.
        WebDriver driverCoupang = new ChromeDriver(options);
        List<String> strArray = new ArrayList<>();
        List<String> priArray = new ArrayList<>();
        List<String> imgArray = new ArrayList<>();
        List<String> siteArray = new ArrayList<>();


        List<Cpu> cpuList = new ArrayList<>();

        try {
                // 광고가 포함되어있음.

            driverCoupang.get("");

        }finally {
            driverCoupang.quit();
        }
    }
}
