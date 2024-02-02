package com.pay.test.movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie {
	@Id
	private int movieCd;

	@Column(length=200)
	private String title;

	@Column(length=200)
	private String imgUrl;

	//   rating symbol
	@Column(length=100)
	private String  rats;

	@Column(length=100)
	//  상영시간
	private String runtime;

	@Column(length=10000)
	private String content;

	//  이미지 저장 ,로 구분
	@Column(length=2000)
	private String posters;

	@Column(length=2000)
	private String stlls;
}
