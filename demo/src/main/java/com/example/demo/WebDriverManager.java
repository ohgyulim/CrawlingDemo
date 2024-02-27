package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// Selenium을 이용하여 크롤링을 하기 위한 객체
public class WebDriverManager {

	private static WebDriver driver;
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	// chromedriver 설치 경로 입력
	private static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";

	private WebDriverManager() {
		// private 생성자로 외부에서 인스턴스 생성을 막음
	}

	public static WebDriver initChromeDriver() {
		setDriverOptions();
		driver = new ChromeDriver(getChromeOptions());
		return driver;
	}

	private static void setDriverOptions() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	}

	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		return options;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
