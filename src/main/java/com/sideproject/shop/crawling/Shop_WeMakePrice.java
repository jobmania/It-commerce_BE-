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

public class Shop_WeMakePrice {
    public static void main(String[] args) {
        //세션시작
        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
//        options.addArguments("headless");   //  실행시 띄우는 창
        options.addArguments("--disable-popup-blocking"); // 팝업 무시

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); //Normal: 로드 이벤트 실행이 반환 될 때 까지 기다린다.
        WebDriver driverWMP = new ChromeDriver(options);
        List<String> strArray = new ArrayList<>();
        List<String> priArray = new ArrayList<>();
        List<String> imgArray = new ArrayList<>();
        List<String> siteArray = new ArrayList<>();
        List<Cpu> cpuList = new ArrayList<>();

        try {
            driverWMP.get("https://search.wemakeprice.com/search?searchType=DEFAULT&search_cate=top&keyword=CPU&isRec=1&_service=5&_type=3&sort=sales&isPopularCategory=Y");
//#_contents > div > div.search_wrap > div:nth-child(1) > div.search_box_listwrap.tab_cont > div > div:nth-child(1) > a > div.list_thum > img

//               // 화면 끝까지 이동해야함.ㅈ

            List<WebElement> elements = driverWMP.findElements(By.className("list_thum"));
            for (WebElement element : elements) {

                String imgUrl = element.findElement(By.tagName("img")).getAttribute("src");
                String itemName = element.findElement(By.tagName("img")).getAttribute("alt");
                System.out.println("imgUrl = " + imgUrl);
                System.out.println("itemName = " + itemName);
                strArray.add(itemName);
                imgArray.add(imgUrl);
            }


        }finally {
            driverWMP.quit();
        }

        System.out.println("imgArray = " + imgArray);
        System.out.println("strArray = " + strArray);

    }
}
