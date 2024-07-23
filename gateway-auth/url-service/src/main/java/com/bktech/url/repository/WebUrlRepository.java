package com.bktech.url.repository;

import java.util.Optional;

import com.bktech.app.repository.AppRepository;
import com.bktech.url.entity.WebUrl;

public interface WebUrlRepository extends AppRepository<WebUrl, Long> {

	Optional<WebUrl> findByHashCode(String hashCode);

}
