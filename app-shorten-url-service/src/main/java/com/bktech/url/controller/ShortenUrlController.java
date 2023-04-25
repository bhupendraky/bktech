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

import com.bktech.url.config.Traceble;
import com.bktech.url.service.ShortenUrlService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/url")
public class ShortenUrlController {

	@Autowired
	private ShortenUrlService shortenUrlService;

	@Traceble
	@ApiOperation("Get the URL")
	@GetMapping("/{hashCode}")
	public String getUrl(@PathVariable String hashCode) {
		return shortenUrlService.getUrl(hashCode);
	}

	@Traceble
	@ApiOperation("Get All URL")
	@GetMapping("/get-all")
	public List<String> getAllUrl() {
		return shortenUrlService.getAllUrl();
	}

	@Traceble
	@ApiOperation("Shorten URL")
	@PostMapping()
	public String shortenUrl(@RequestBody String url) {
		return shortenUrlService.shortenUrl(url);
	}

	@ApiOperation("Get the next counter value")
	@GetMapping("/next/counter")
	public Long getNextCounterValue() {
		return shortenUrlService.getNextCounterValue();
	}

	@ApiOperation("Delete all URLs")
	@DeleteMapping("/delete-all")
	public String deleteAllUrl() {
		return shortenUrlService.deleteAllUrl();
	}

}
