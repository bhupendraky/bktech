package com.techy.url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techy.url.data.UrlJpaRepository;
import com.techy.url.errors.UrlErrorCode;
import com.techy.url.errors.UrlServiceException;

@Service
public class UrlService {

	@Autowired
	private UrlJpaRepository urlRepository;

	public String getUrl(String hashCode) {
		return urlRepository.findById(hashCode)
		.map(e -> e.getUrl())
		.orElseThrow(() -> new UrlServiceException(UrlErrorCode.TS_03_0001));
	}

	public String shortenUrl(String url) {
		return null;
	}

}
