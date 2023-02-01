package com.sideproject.shop.crawling;

import com.sideproject.shop.domain.itemList.Cpu;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class Shop_Gmarket {

    public static void main(String[] args) {

        //세션시작
        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
//        options.addArguments("headless");   //  실행시 띄우는 창
        options.addArguments("--disable-popup-blocking"); // 팝업 무시

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); //Normal: 로드 이벤트 실행이 반환 될 때 까지 기다린다.
        WebDriver driverGmark = new ChromeDriver(options);
        List<String> strArray = new ArrayList<>();
        List<String> priArray = new ArrayList<>();
        List<String> imgArray = new ArrayList<>();
        List<String> siteArray = new ArrayList<>();


        List<Cpu> cpuList = new ArrayList<>();

        try {
            driverGmark.get("https://browse.gmarket.co.kr/search?keyword=cpu&s=8");

            List<WebElement> elementsImages = driverGmark.findElements(By.className("box__image"));

            List<WebElement> elements = driverGmark.findElements(By.className("box__information"));

            for (int i = 0; i < elementsImages.size(); i++) {
                String siteUrl = elementsImages.get(i).findElement(By.tagName("a")).getAttribute("href");
                String imgUrl = elementsImages.get(i).findElement(By.tagName("a")).findElement(By.tagName("img")).getAttribute("src");
                String itemName = elements.get(i).findElement(By.className("box__item-title")).findElement(By.className("text__item")).getAttribute("title");
                String itemPrice = elements.get(i).findElement(By.tagName("strong")).getText();

                Cpu cpu = new Cpu(itemName,itemPrice);
                cpuList.add(cpu);

                strArray.add(itemName);
                priArray.add(itemPrice);
                imgArray.add(imgUrl);
                siteArray.add(siteUrl);

            }


        }finally {
            driverGmark.quit();
        }

        System.out.println(strArray);
        System.out.println(priArray);
        System.out.println(imgArray);
        System.out.println(siteArray);
        Cpu cpu0 = cpuList.get(0);
        System.out.println(cpu0.toString());
    }
}
