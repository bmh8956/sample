package com.pay.test;


import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelTest {
//	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
//	public static final String WEB_DRIVER_PATH = "C:\\chrome\\chromedriver.exe"; //드라이버 경로

	public static void main(String[] args) throws InterruptedException {
//		try {
//			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		//크롬 설정을 담은 객체 생성
		WebDriverManager.edgedriver().setup();
//		ChromeDriver driver = new ChromeDriver();
		EdgeDriver driver = new EdgeDriver();
		// 2. 웹 페이지 접속
//		String baseUrl = "https://www.op.gg/summoners/kr/jugklng";
		String baseUrl = "https://www.lottecinema.co.kr/NLCHS/Movie/List?flag=1";
		// String searchTerm = "Java";
		// String url = baseUrl + "/wiki/" + searchTerm;

		driver.get(baseUrl);

		Thread.sleep(1);
		List<WebElement> elements = driver.findElements(By.cssSelector(".screen_add_box"));
		List<String> imgl = new ArrayList<>();
		List<String> titlel = new ArrayList<>();
		List<Map> list = new ArrayList<>();


		for(WebElement element : elements) {
			JsonObject jo = new JsonObject();
			String img = extractText(element, ".poster_info img", "src");
			String title = extractText(element, ".tit_info", "text").replace(extractText(element, ".ic_grade", "text"), "");
			imgl.add(img);
			titlel.add(title);
		}
		System.out.println(imgl);
		System.out.println(titlel);

//		List<WebElement> sentence = driver.findElements(By.className("tier"));
//		for (WebElement webElement : sentence) {
//			System.out.println(sentence.get(0).getText());
//		}
		driver.quit();

	}

	private static String extractText(WebElement parentElement, String selector, String attribute) {
		if(attribute.equals("text")) {
			try {
				WebElement childElement = parentElement.findElement(By.cssSelector(selector));
				return childElement.getText();
			} catch (Exception e) {
				// 요소가 없을 경우 예외 처리
				return "";
			}
		} else {
			try {
				WebElement childElement = parentElement.findElement(By.cssSelector(selector));
				return childElement.getAttribute(attribute);
			} catch (Exception e) {
				// 요소가 없을 경우 예외 처리
				return "";
			}
		}
	}
}

