package com.bktech.url.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bktech.url.domain.WebUrl;

public interface WebUrlRepository extends JpaRepository<WebUrl, Long> {

	Optional<WebUrl> findByHashCode(String hashCode);

}
