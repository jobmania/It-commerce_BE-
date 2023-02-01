package com.sideproject.shop.crawling;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest {

    public static void main(String[] args){

        // 11번가


        String word = "CPU";

        StringBuffer sb = new StringBuffer("https://search.11st.co.kr/Search.tmall?kwd=");
        sb.append(word);
        // 11번가 인기순으로 sort
        sb.append("#sortCd%%SPS%%11번가%20인기순%%1$$pageNum%%1%%page%%2");

        String uri = sb.toString();
        System.out.println(uri); // 정상적으로 uri 입력 완료!


        ///////   크롤링  /////////// CSS Selector를 사용하여 HTML의 데이터를 추출할 수 있는 Java 라이브러리이다.

        try{
            Document document = Jsoup.connect(uri).get();  // HTML 추출..
//            System.out.println(document);  // 잘들고옴

            Elements contents = document.select("div.c_prd_thumb a"); ///  들고 오는게 문제임..
            for (Element content : contents) {

                System.out.println((content==null));
            }


            System.out.println("==================");


            System.out.println("!!!!!!긑!!!!!!");
        }catch (IOException e){
           e.getMessage();
        }


    }




}
