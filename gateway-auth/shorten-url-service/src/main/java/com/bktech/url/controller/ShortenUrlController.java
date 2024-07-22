package com.bktech.url.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.infra.config.Traceble;
import com.bktech.url.service.ShortenUrlService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/url")
public class ShortenUrlController {

	@Autowired
	private ShortenUrlService shortenUrlService;

	@Traceble
	@GetMapping("/{hashCode}")
	public String getUrl(@PathVariable String hashCode) {
		return shortenUrlService.getUrl(hashCode);
	}

	@Traceble
	@GetMapping("/get-all")
	public List<String> getAllUrl() {
		return shortenUrlService.getAllUrl();
	}

	@Traceble
	@PostMapping()
	public String shortenUrl(@RequestBody String url) {
		return shortenUrlService.shortenUrl(url);
	}

	@GetMapping("/next/counter")
	public Long getNextCounterValue() {
		return shortenUrlService.getNextCounterValue();
	}

	@DeleteMapping("/delete-all")
	public String deleteAllUrl() {
		return shortenUrlService.deleteAllUrl();
	}

}
