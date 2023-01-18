package com.sideproject.shop;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        // 11번가


        String word = "CPU";

        StringBuffer sb = new StringBuffer("https://search.11st.co.kr/Search.tmall?kwd=");
        sb.append(word);
        // 11번가 인기순으로 sort
        sb.append("#sortCd%%SPS%%11번가%20인기순%%1$$pageNum%%1%%page%%2");

        String uri = sb.toString();
        System.out.println(uri);


        ///////   크롤링  /////////// CSS Selector를 사용하여 HTML의 데이터를 추출할 수 있는 Java 라이브러리이다.

        Document document = Jsoup.connect(uri).get();  // HTML 추출..
        System.out.println(document);  // 잘들고옴

        Elements images = document.getElementsByClass("c_prd_thumb");
        Elements info = document.getElementsByClass("c_prd_name c_prd_name_row_1");

        for (Element image : images) {
            System.out.println(image);
        }


        System.out.println("긑");
    }




}
