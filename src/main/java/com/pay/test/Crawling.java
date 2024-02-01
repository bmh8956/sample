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

	public static String crawling() throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		String baseUrl = "https://www.lottecinema.co.kr/NLCHS/Movie/List?flag=1";

		EdgeOptions options = new EdgeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		EdgeDriver driver = new EdgeDriver(options);
		driver.get(baseUrl);

//		Thread.sleep(1);
		List<WebElement> elements = driver.findElements(By.cssSelector(".screen_add_box"));
		List<String> imgl = new ArrayList<>();
		List<String> titlel = new ArrayList<>();
		List<Map> list = new ArrayList<>();
		JsonArray ja = new JsonArray();

		for(WebElement element : elements) {
			JsonObject jo = new JsonObject();
			String img = extractText(element, ".poster_info img", "src");
			String title = extractText(element, ".tit_info", "text").replace(extractText(element, ".ic_grade", "text"), "");
			imgl.add(img);
			titlel.add(title);
			jo.addProperty("title", title);
			jo.addProperty("img", img);
			ja.add(jo);
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
}
