package com.pay.test.templates;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class temController {

	// 메인 index
	@GetMapping("/")
	public String temIndex() {
		return "temp/index";
	}
}
