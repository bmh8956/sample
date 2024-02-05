package com.pay.test;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

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
		String movieCd = "20701";
		WebDriverManager.edgedriver().setup();
		String baseUrl = "https://www.lottecinema.co.kr/NLCHS/Movie/MovieDetailView?movie=" + movieCd;

		EdgeOptions options = new EdgeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		EdgeDriver driver = new EdgeDriver(options);
		driver.get(baseUrl);

		JsonObject jo = new JsonObject();

		String date = extractText(driver.findElement(By.cssSelector(".mov_info1")), "li:first-child span.roboto", "text");
		String runTime = extractText(driver.findElement(By.cssSelector(".mov_info1")), "li span.time span.roboto", "text");
		String rats = extractText(driver.findElement(By.cssSelector(".mov_info1")), "li span.grade_txt.gr_all span.roboto", "text");
		String content = extractText(driver.findElement(By.cssSelector(".txtarea_box.movdetailtxt")), ".txtarea", "text");
		String gen = extractText(driver.findElement(By.cssSelector(".movi_tab_info1 .detail_info2")), "li:first-child span", "text");
		String dir = extractText(driver.findElement(By.cssSelector(".movi_tab_info1 .detail_info2")), "li:nth-child(2) span.line_type", "text");
		String actors = extractText(driver.findElement(By.cssSelector(".movi_tab_info1 .detail_info2")), "li:nth-child(3) span.line_type ", "text");
//		List<WebElement> trailers = driver.findElements(By.cssSelector(".layerMovieTrailer .layer_contents"));
		List<WebElement> stlls = driver.findElements(By.cssSelector(".stillcut_list .gridlist .gridlist_item"));

//		JsonArray ja_tra = new JsonArray();
//		for(WebElement tra : trailers) {
//			ja_tra.add(extractText(tra, "video", "src"));
//		}
		JsonArray ja_stl = new JsonArray();
		for(WebElement stl : stlls) {
			ja_stl.add(extractText(stl, "a img", "src"));
		}


		jo.addProperty("date", date);
		jo.addProperty("runTime", runTime);
		jo.addProperty("rats", rats);
		jo.addProperty("content", content);
		jo.addProperty("gen", gen);
		jo.addProperty("dir", dir);
		jo.addProperty("actors", actors);
//		jo.addProperty("tra", ja_tra.toString());
		jo.addProperty("stl", ja_stl.toString());

		System.out.println(jo);

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

