package com.bktech.user.repository;

import java.util.Optional;

import com.bktech.app.repository.AppRepository;
import com.bktech.user.entity.Role;

public interface RoleRepository extends AppRepository<Role, Long> {

	boolean existsByName(String name);

	Optional<Role> findByName(String name);

}
