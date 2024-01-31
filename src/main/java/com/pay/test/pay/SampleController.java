package com.pay.test.pay;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class SampleController {

	@Setter(onMethod_ = @Autowired)
	private KakaoPay kakaopay;

	@GetMapping("/")
	public String index() {
		return "req";
	}

	@GetMapping("/kakaoPay")
	public void kakaoPayGet() {

	}

	@PostMapping("/kakaoPay")
	@ResponseBody
	public String kakaoPay(@RequestParam Map<String, Object> map) {
		log.info(".....................kakaoPay post.......................");
		log.info((String) map.get("item"));
		log.info((String) map.get("price"));
		String item = (String) map.get("item");
		String price = (String) map.get("price");
		String next_url = kakaopay.kakaoPayReady(item, price);

//		return "redirect:" + kakaopay.kakaoPayReady();
		return next_url;
	}

	@RequestMapping("/kakaoPaySuccess")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		log.info("......................kakaoPaySuccess get......................");
		log.info("kakaoPaySuccess pg_token : " + pg_token);

		model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
//		return "kakaoPaySuccess";
	}

	@RequestMapping("/kakaoPaySuccessFail")
	public String kakaoPaySuccessFail() {
		return "kakaoPaySuccessFail";
	}

	@RequestMapping("/kakaoPayCancel")
	public String kakaoPayCancel() {
		return "kakaoPayCancel";
	}

}
