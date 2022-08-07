package com.techy.url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techy.url.data.CounterRepository;
import com.techy.url.data.WebUrlRepository;
import com.techy.url.domain.Counter;
import com.techy.url.errors.ShortenUrlErrorCode;
import com.techy.url.errors.ShortenUrlServiceException;

@Service
public class ShortenUrlService {

	@Autowired
	private WebUrlRepository webUrlRepository;
	
	@Autowired
	private CounterRepository counterRepository;

	public String getUrl(String hashCode) {
		return webUrlRepository.findById(hashCode)
		.map(e -> e.getUrl())
		.orElseThrow(() -> new ShortenUrlServiceException(ShortenUrlErrorCode.TS_03_0001));
	}

	public String shortenUrl(String url) {
		return null;
	}

	@Transactional
	public Long getNextCounterValue() {
		Counter counter = counterRepository.getOne(1);
		counter.setValue(counter.getValue()+1);
		counterRepository.save(counter);
		return counter.getValue();
	}

}
