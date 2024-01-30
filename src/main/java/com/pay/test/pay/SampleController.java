package com.pay.test.pay;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

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
	public String kakaoPay() {
		log.info(".....................kakaoPay post.......................");

		return "redirect:" + kakaopay.kakaoPayReady();

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
