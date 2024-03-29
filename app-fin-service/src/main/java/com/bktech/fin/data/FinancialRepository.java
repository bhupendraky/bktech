package com.bktech.fin.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bktech.fin.domain.UserAccount;

public interface FinancialRepository extends JpaRepository<UserAccount, Long> {

}
