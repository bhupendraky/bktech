package com.techy.fin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techy.fin.data.FinancialRepository;

@Service
public class FincialService {


	@Autowired
	private FinancialRepository repo;

}
