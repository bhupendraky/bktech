package com.techy.fin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techy.fin.data.FinancialRepository;
import com.techy.fin.domain.UserAccount;
import com.techy.user.proxy.UserServiceProxy;

@Service
public class FincialService {


	@Autowired
	private FinancialRepository repo;

	@Autowired
	private UserServiceProxy userProxy;

	public List<UserAccount> getAllAccounts() {
		return repo.findAll();
	}

	public String getUserId() {
		return userProxy.getUserId();
	}

}
