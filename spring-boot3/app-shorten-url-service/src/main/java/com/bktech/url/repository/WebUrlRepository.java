package com.bktech.url.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bktech.url.entity.WebUrl;

public interface WebUrlRepository extends JpaRepository<WebUrl, Long> {

	Optional<WebUrl> findByHashCode(String hashCode);

}
