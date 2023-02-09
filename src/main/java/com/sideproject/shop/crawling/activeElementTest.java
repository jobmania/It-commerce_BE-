package com.sideproject.shop.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class activeElementTest {
    // 검색할 키워드
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriver driver2 = new ChromeDriver();
        try {
            driver.get("http://www.google.com");
            driver.findElement(By.cssSelector("[name='q']")).sendKeys("iphone");

            // Get attribute of current active element
            String attr = driver.switchTo().activeElement().getAttribute("title");
            System.out.println(attr);
        } finally {
//            driver.quit();
        }
    }
}