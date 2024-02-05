package com.pay.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Crawling {

	public static String crawling_movie_list() throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		String baseUrl = "https://www.lottecinema.co.kr/NLCHS/Movie/List?flag=1";

		EdgeOptions options = new EdgeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		EdgeDriver driver = new EdgeDriver(options);
		driver.get(baseUrl);

//		Thread.sleep(1);
		List<WebElement> elements = driver.findElements(By.cssSelector(".screen_add_box"));
		JsonArray ja = new JsonArray();

		for(WebElement element : elements) {
			JsonObject jo = new JsonObject();
			String img = extractText(element, ".poster_info img", "src");
			String title = extractText(element, ".tit_info", "text").replace(extractText(element, ".ic_grade", "text"), "");
			if(!(title.equals("") || img.equals(""))) {
				jo.addProperty("title", title);
				jo.addProperty("img", img);
				ja.add(jo);
//			    ReadImage.save(img);
			}
		}
		driver.quit();
		Gson gson = new Gson();
		String json = gson.toJson(ja);
		return json;
	}

	public static String extractText(WebElement parentElement, String selector, String attribute) {
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

	public static void get_movie(String movieCd) {
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
		List<String> stlls = new ArrayList<>();
		List<String> trailers = new ArrayList<>();

		jo.addProperty("date", date);
		jo.addProperty("runTime", runTime);
		jo.addProperty("rats", rats);
		jo.addProperty("content", content);
		jo.addProperty("gen", gen);
		jo.addProperty("dir", dir);
		jo.addProperty("actors", actors);

		System.out.println(jo);
	}

}
