package com.bktech.fin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bktech.fin.data.FinancialRepository;
import com.bktech.fin.domain.UserAccount;
import com.bktech.user.proxy.UserServiceProxy;

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
