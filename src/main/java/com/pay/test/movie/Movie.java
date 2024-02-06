package com.pay.test.movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Movie {
	@Id
	@Column(length=10)
	private String movieCd;

	@Column(length=200)
	private String title;

	@Column(length=200)
	private String imgUrl;

	//   등급
	@Column(length=100)
	private String  rats;

	//  상영시간
	@Column(length=100)
	private String runtime;

	@Column(length=10000)
	private String content;

	//  이미지 저장 ,로 구분
	@Column(length=2000)
	private String posters;

	@Column(length=2000)
	private String stlls;

	//  장르
	@Column(length = 100)
	private String Genre;

	@Column(length = 200)
	private String actors;

	@Column(length = 100)
	private String director;

	@Column(length = 500)
	private String trailerImg;

	@Column(length = 500)
	private String trailerVideo;

	@Column(updatable = false)
	private LocalDateTime regDate;

	private LocalDateTime delDate;

	private LocalDateTime screenOutDate;
}
