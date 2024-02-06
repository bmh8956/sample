package com.pay.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pay.test.movie.Movie;
import com.pay.test.movie.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ApiController {
	private final MovieService movieService;

    @RequestMapping("/movie01")
	public String movie01() {
		return "movie01";
	}

	@RequestMapping("/movie02")
	public String movie02() {
		return "movie02";
	}

	@RequestMapping("/movie03")
	public String movie03() {
		return "movie03";
	}

	@RequestMapping("/movie04")
	public String movie04() {
		return "movie04";
	}

	@RequestMapping("/movie05")
	public String movie05() {
		return "movie05";
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
//		ReadImage.save("https://cf.lottecinema.co.kr//Media/MovieFile/MovieImg/202401/20701_103_1.jpg");
		return "redirect:/crw_test";
	}

	@PostMapping("/update_movie_list")
	@ResponseBody
	public ResponseEntity<String> updateMovie(@RequestBody List<Movie> mvs) {
		JsonObject jo = new JsonObject();
		for(Movie mv : mvs) {
			movieService.create(mv);
		}
		jo.addProperty("msg", "success");

		System.out.println(jo);

		return new ResponseEntity<>(jo.toString(), HttpStatus.OK);
	}

	@PostMapping("/crawling/get_details")
	@ResponseBody
	public ResponseEntity<String> get_detail(@RequestBody List<Movie> mvs) throws InterruptedException {
		JsonArray ja = new JsonArray();
		Gson gson = new Gson();
		String data = "";
		for(Movie mv : mvs) {
			System.out.println(mv.getMovieCd());
			Movie movie = Crawling.get_movie(mv);
			movieService.create(movie);
			ja.add(mv.getMovieCd());
		}
//		JsonObject jo = new JsonObject();
//		jo.addProperty("msg", "success");
//		jo.addProperty("list", String.valueOf(ja));
		return new ResponseEntity<>(ja.toString(), HttpStatus.OK);
	}

}
