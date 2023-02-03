package com.sideproject.shop.crawling;

import com.sideproject.shop.domain.itemList.Cpu;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class Shop_Auction {
    //https://browse.auction.co.kr/search?encKeyword=cpu&s=8
    public static void main(String[] args) {
        //세션시작
        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
//        options.addArguments("headless");   //  실행시 띄우는 창
        options.addArguments("--disable-popup-blocking"); // 팝업 무시
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); //Normal: 로드 이벤트 실행이 반환 될 때 까지 기다린다.

        WebDriver driverAuction = new ChromeDriver(options);


        List<String> strArray = new ArrayList<>();
        List<String> priArray = new ArrayList<>();
        List<String> imgArray = new ArrayList<>();
        List<String> siteArray = new ArrayList<>();


        List<Cpu> cpuList = new ArrayList<>();

        String abc = null;

        try {
            driverAuction.get("https://browse.auction.co.kr/search?keyword=CPU&encKeyword=CPU&dom=auction&isSuggestion=No&s=8");
            WebElement itemCard = driverAuction.findElement(By.className("section--itemcard"));
            var stTime = new Date().getTime(); //현재시간
            while (new Date().getTime() < stTime + 10000) { //10초 동안 무한스크롤 지속
                Thread.sleep(500); //0.1초 기다림. 로딩시간
                ((JavascriptExecutor)driverAuction).executeScript("window.scrollTo(0, document.body.scrollHeight)", itemCard);
            }



            List<WebElement> elements = new WebDriverWait(driverAuction, Duration.ofSeconds(3))
                    .until(driver -> driver.findElements(By.className("section--itemcard"))); ;


            for (WebElement element : elements) {

                String siteUrl = element.findElement(By.className("section--itemcard_img")).findElement(By.tagName("a")).getAttribute("href");
                String itemName =element.findElement(By.className("text--title")).getText();
                String itemPrice = element.findElement(By.className("text--price_seller")).getText();
                // NoSuchElementException: no such element: Unable to locate element: {"method":"tag name","selector":"img"}
//                String imgUrl = element.findElement(By.className("section--itemcard_img")).findElement(By.tagName("img")).getAttribute("src");
                System.out.println("==========================================================");
//                System.out.println(imgUrl);
//                  #section--inner_content_body_container > div:nth-child(2) > div:nth-child(11) > div > div > div.section--itemcard_img > a > img
//                #section--inner_content_body_container > div:nth-child(4) > div:nth-child(15) > div > div > div.section--itemcard_img > a > img
                Cpu cpu = new Cpu(itemName, itemPrice);
                cpuList.add(cpu);
                strArray.add(itemName);
                priArray.add(itemPrice);
                siteArray.add(siteUrl);
//                imgArray.add(imgUrl);

            }


//            // 이미지만 4개이상 못들고온다?
//            List<WebElement> elementImg = new WebDriverWait(driverAuction, Duration.ofSeconds(3)) //대기대기!
//                    .until(driver -> driver.findElements(By.className("image--itemcard")));
//
//            // 화면 끝까지 이동해야함.
//
//            for (WebElement element : elementImg) {
//
//                String imgUrl = element.getAttribute("src");
//                imgArray.add(imgUrl);
//            }



        } catch (NoSuchElementException e){

            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driverAuction.quit();

        }


        System.out.println(strArray);
        System.out.println(priArray);
        System.out.println(siteArray);
        System.out.println(imgArray);
        Cpu cpu0 = cpuList.get(2);
        System.out.println(cpu0.toString());

    }
}
