package com.techy.url.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techy.url.domain.WebUrl;

@Repository
public interface UrlJpaRepository extends JpaRepository<WebUrl, String> {
	
}
