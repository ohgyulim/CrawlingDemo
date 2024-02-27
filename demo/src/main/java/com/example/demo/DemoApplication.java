package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		// 크롤링 대상 url: https://groundseesaw.co.kr/product/list.html?cate_no=47
		CrawlerGroundSeeSaw crawlerGroundSeeSaw = new CrawlerGroundSeeSaw();
		crawlerGroundSeeSaw.startCrawling();
	}
}
