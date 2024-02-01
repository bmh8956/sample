package com.pay.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {

	@RequestMapping("/movie01")
	public String movie01() {
		return "movie01";
	}

	@RequestMapping("/movie02")
	public String movie02() {
		return "movie02";
	}

	@RequestMapping("/crw_test")
	public String crawling() {
		return "crawling";
	}

	@RequestMapping("/get_info")
	@ResponseBody
	public String get_info() throws InterruptedException {
		String data = Crawling.crawling();
		System.out.println(data);
		return data;
	}
}
