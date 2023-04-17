package com.bktech.user.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bktech.user.domain.Role;

@Repository
public interface AdminRepository extends JpaRepository<Role, Long> {

	boolean existsByName(String name);

}
