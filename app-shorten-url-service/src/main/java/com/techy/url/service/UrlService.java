package com.techy.url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techy.url.data.CounterJpaRepository;
import com.techy.url.data.WebUrlJpaRepository;
import com.techy.url.domain.Counter;
import com.techy.url.errors.UrlErrorCode;
import com.techy.url.errors.UrlServiceException;

@Service
public class UrlService {

	@Autowired
	private WebUrlJpaRepository urlRepository;
	
	@Autowired
	private CounterJpaRepository counterRepository;

	public String getUrl(String hashCode) {
		return urlRepository.findById(hashCode)
		.map(e -> e.getUrl())
		.orElseThrow(() -> new UrlServiceException(UrlErrorCode.TS_03_0001));
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
