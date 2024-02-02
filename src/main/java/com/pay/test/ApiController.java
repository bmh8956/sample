package com.pay.test;

import com.google.gson.JsonObject;
import com.pay.test.movie.Movie;
import com.pay.test.movie.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class ApiController {
	MovieService movieService;

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
		log.info("============crawling start==========");
		String data = Crawling.crawling_movie_list();
		log.info("============crawling end============");
//		System.out.println(data);
		return data;
	}

	@RequestMapping("/imgDown")
	public String down() {
		ReadImage.save("https://cf.lottecinema.co.kr//Media/MovieFile/MovieImg/202401/20701_103_1.jpg");
		return "redirect:/crw_test";
	}

	@PostMapping("/update_movie_list")
	@ResponseBody
	public JsonObject updateMovie(@RequestParam List<Movie> mvs) {
		JsonObject jo = new JsonObject();
		for(Movie mv : mvs) {
			movieService.create(mv);
		}

		return jo;
	}
}
