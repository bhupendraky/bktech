package com.techy.url.service;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techy.url.data.CounterRepository;
import com.techy.url.data.WebUrlRepository;
import com.techy.url.domain.Counter;
import com.techy.url.domain.WebUrl;
import com.techy.url.errors.ShortenUrlErrorCode;
import com.techy.url.errors.ShortenUrlServiceException;

@Service
public class ShortenUrlService {
	
	private static String base62Alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Autowired
	private WebUrlRepository webUrlRepository;

	@Autowired
	private CounterRepository counterRepository;

	public String getUrl(String hashCode) {
		return webUrlRepository.findByHashCode(hashCode)
				.map(e -> e.getUrl())
				.orElseThrow(() -> new ShortenUrlServiceException(ShortenUrlErrorCode.TS_03_0001));
	}

	@Transactional
	public String shortenUrl(String url) {
		UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });
		if(!urlValidator.isValid(url)) {
			throw new ShortenUrlServiceException(ShortenUrlErrorCode.TS_03_0002);
		}
		Long nextCouterValue = getNextCounterValue();
		String hashCode = generateBase62Hash(nextCouterValue);
		WebUrl webUrl = new WebUrl(hashCode, url);
		webUrlRepository.save(webUrl);
		
		return hashCode;
	}

	private String generateBase62Hash(Long counter) {
		StringBuffer hashStr = new StringBuffer();
		while(counter > 0) {
			long r = counter % 62;
			hashStr.append(base62Alphabets.charAt((int)r));
			counter = counter / 62;
		}
		return hashStr.reverse().toString();
	}

	@Transactional
	public Long getNextCounterValue() {
		Counter counter = counterRepository.getOne(1);
		counter.setValue(counter.getValue() + 1);
		counterRepository.save(counter);
		return counter.getValue();
	}

}
