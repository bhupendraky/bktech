package com.tech.hub.fin.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.hub.fin.domain.UserAccount;

@Repository
public interface FinancialRepository extends JpaRepository<UserAccount, Long> {

}
