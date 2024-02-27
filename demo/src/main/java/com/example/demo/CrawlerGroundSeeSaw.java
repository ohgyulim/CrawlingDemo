package com.example.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CrawlerGroundSeeSaw {

	// 크롤링할 대상 url (가장 상위 페이지)
	private static final String MAIN_PAGE_URL = "https://groundseesaw.co.kr/product/list.html?cate_no=47";
	private WebDriver driver = WebDriverManager.initChromeDriver();
	private HashMap<Integer, PopUpStoreInfo> popUpStoreInfos = new HashMap<>();
	private HashMap<Integer, String> detailPageUrls = new HashMap<>();

	// 0. 크롤링 시작
	public void startCrawling() {
		setCrawler();
		getMainPageInfos();
	}

	// 1. 크롤러 객체에 url 할당
	private void setCrawler() {
		// 가장 상위 페이지에서 정보를 가져옴
		driver.get(MAIN_PAGE_URL);
	}

	// 2. 크롤링 할 정보 추출
	private void getMainPageInfos() {
		var mainPageInfos = driver.findElement(By.cssSelector(".prdList.grid2"))
			.findElements(By.className("description"));
		setPopUpStoreInfos(mainPageInfos);
	}

	// 3. 데이터 가공
	private void setPopUpStoreInfos(List<WebElement> infos) {
		for (int i = 0; i < infos.size(); i++) {
			PopUpStoreInfo data = new PopUpStoreInfo(null, null, null, null, null, null, null, null,
				-1, null);

			String name = infos.get(i).findElement(By.cssSelector(".name.shop-item-title"))
				.getText();
			String region = infos.get(i).findElement(By.cssSelector(".custom_option2.xans-record-"))
				.getText();
			String[] date = infos.get(i).findElement(By.cssSelector(".custom_option1.xans-record-"))
				.getText().split(" ~ ");

			LocalDate startDate = isMatchingPattern(date[0]) ? LocalDate.parse(date[0],
				DateTimeFormatter.ofPattern("yy.MM.dd(E)", Locale.KOREAN)) : null;
			LocalDate endDate = isMatchingPattern(date[1]) ? LocalDate.parse(date[1],
				DateTimeFormatter.ofPattern("yy.MM.dd(E)", Locale.KOREAN)) : null;

			data.setName(name);
			data.setRegion(region);
			data.setStartDate(startDate);
			data.setEndDate(endDate);
			popUpStoreInfos.put(i, data);

			// 상위 페이지 하위에 있는 각각의 팝업 스토어 url
			String detailPageUrl = infos.get(i).findElement(By.tagName("a")).getAttribute("href");
			detailPageUrls.put(i, detailPageUrl);
		}
		// 4. 팝업 스토어 상세 페이지에 접속하여 데이터 추가 크롤링
		getDetailInfos();
	}

	private static boolean isMatchingPattern(String dateString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd(E)", Locale.KOREAN);
			LocalDate.parse(dateString, formatter);
			return true; // 매치됨
		} catch (Exception e) {
			return false; // 매치되지 않음
		}
	}

	// 4. 상세 url 하나씩 접근
	private void getDetailInfos() {
		for (var detailPageUrlMap : detailPageUrls.entrySet()) {
			driver.get(detailPageUrlMap.getValue());
			setDetailInfos(detailPageUrlMap.getKey());
		}
	}

	// 5. 데이터 추가 크롤링 및 저장
	private void setDetailInfos(int key) {
		var detailInfos = driver.findElement(By.className("visit-infos"))
			.findElements(By.tagName("li"));

		for (var detailInfo : detailInfos) {
			String title = detailInfo.findElement(By.cssSelector("div.title")).getText();
			switch (title) {
				case "전시 장소" -> popUpStoreInfos.get(key)
					.setAddress(detailInfo.findElement(By.tagName("p")).getText());
				case "티켓 가격" -> popUpStoreInfos.get(key).setTicketPrice(Integer.parseInt(
					detailInfo.findElement(By.tagName("p")).getText().replaceAll("[^\\d]", "")));
				case "환불 규정" -> popUpStoreInfos.get(key)
					.setRefundPolicy(detailInfo.findElement(By.tagName("p")).getText());
			}
		}
	}
}
