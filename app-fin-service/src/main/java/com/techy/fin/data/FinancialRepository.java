package com.techy.fin.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techy.fin.domain.UserAccount;

@Repository
public interface FinancialRepository extends JpaRepository<UserAccount, Long> {

}
