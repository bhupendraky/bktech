package com.bktech.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bktech.user.data.AdminRepository;
import com.bktech.user.domain.Role;
import com.bktech.user.execp.AppException;
import com.bktech.user.execp.ExceptionCode;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public Role createRole(String name) {
		if(adminRepository.existsByName(name)) {
			throw new AppException(ExceptionCode.USRSVC_0006, name);
		}
		return adminRepository.save(new Role(name));
	}

}
