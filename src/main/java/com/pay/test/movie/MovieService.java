package com.pay.test.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

	private final MovieRepository mvRepository;

	public void create(Movie mv) {
		mvRepository.save(mv);
	}

}
