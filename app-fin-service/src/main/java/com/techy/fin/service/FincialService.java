package com.techy.fin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techy.fin.data.FinancialRepository;
import com.techy.fin.domain.UserAccount;

@Service
public class FincialService {


	@Autowired
	private FinancialRepository repo;

	public List<UserAccount> getAllAccounts() {
		return repo.findAll();
	}

}
