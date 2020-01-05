package com.bky.user.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bky.user.domain.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

}
