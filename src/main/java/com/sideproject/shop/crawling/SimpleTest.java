package com.sideproject.shop.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SimpleTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driverAuction = new ChromeDriver();

        driverAuction.get("https://browse.auction.co.kr/search?keyword=아이폰&encKeyword=CPU&dom=auction&isSuggestion=No&s=8");



        List<WebElement> elements = new WebDriverWait(driverAuction, Duration.ofSeconds(10))
                .until(driver -> driver.findElements(By.className("image--itemcard"))); ;



        for (WebElement element : elements) {
//            Thread.sleep(1000); //1초 기다림. 로딩시간
            String src = element.getAttribute("src");
            System.out.println(src);
        }
        driverAuction.quit();


    }
}
