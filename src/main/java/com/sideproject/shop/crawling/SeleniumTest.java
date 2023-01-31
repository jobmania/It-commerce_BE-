package com.sideproject.shop.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class SeleniumTest {

    public static void main(String[] args) {


        //세션시작
        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.


        //페이지가 로드될 때까지 대기
        //Normal: 로드 이벤트 실행이 반환 될 때 까지 기다린다.
        options.addArguments("headless");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        options.addArguments("--disable-popup-blocking"); // 팝업 무시
        WebDriver driver = new ChromeDriver(options);
        List<String> strArray = new ArrayList<>();
        List<String> priArray = new ArrayList<>();
        try {
            driver.get("https://search.11st.co.kr/Search.tmall?kwd=cpu");
            List<WebElement> elements = driver.findElements(By.className("c_card"));




            for (WebElement element : elements) {
                System.out.println("===============================================================");
//                이름
                String itemName = element.findElement(By.className("c_prd_name")).findElement(By.tagName("strong")).getText();
                strArray.add(itemName);

                //가격
                String text = element.findElement(By.className("c_prd_price")).findElement(By.className("value")).getText();
                priArray.add(text);
                System.out.println("=+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            }



        }finally {
            driver.quit(); //세션 종료도 해줘야 한다.브라우저 세션이 끝나면 닫기 대신 종료를 호출한다.

        }


        System.out.println(strArray);
        System.out.println(priArray);



    }
}
