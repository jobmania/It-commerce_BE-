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

public class Shop_11st {
    public static void main(String[] args) {


        //세션시작
        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
//        options.addArguments("headless");   //  실행시 띄우는 창
        options.addArguments("--disable-popup-blocking"); // 팝업 무시
        //페이지가 로드될 때까지 대기
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); //Normal: 로드 이벤트 실행이 반환 될 때 까지 기다린다.
        WebDriver driver11st = new ChromeDriver(options);  // 11번가
        List<String> strArray = new ArrayList<>();
        List<String> priArray = new ArrayList<>();
        List<Cpu> cpuList = new ArrayList<>();


        try {
            driver11st.get("https://search.11st.co.kr/Search.tmall?kwd=cpu#sortCd%%SPS%%11번가%20인기순%%1$$pageNum%%1%%page%%2");
            List<WebElement> elements = driver11st.findElements(By.className("c_card"));




            for (WebElement element : elements) {

                //이름
                String itemName = element.findElement(By.className("c_prd_name")).findElement(By.tagName("strong")).getText();
                strArray.add(itemName);

                //가격
                String price = element.findElement(By.className("c_prd_price")).findElement(By.className("value")).getText();
                priArray.add(price);

                //이미지 url




                Cpu cpu = new Cpu(itemName,price);
                cpuList.add(cpu);

            }



        }finally {
            driver11st.quit(); //세션 종료도 해줘야 한다.브라우저 세션이 끝나면 닫기 대신 종료를 호출한다.

        }


        System.out.println(strArray);
        System.out.println(priArray);
        System.out.println(cpuList.get(0).toString()); // 저장 ok



    }

}
