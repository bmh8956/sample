package com.pay.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
