package com.tech.hub.url.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.hub.url.domain.WebUrl;

@Repository
public interface WebUrlRepository extends JpaRepository<WebUrl, Long> {

	Optional<WebUrl> findByHashCode(String hashCode);

}
