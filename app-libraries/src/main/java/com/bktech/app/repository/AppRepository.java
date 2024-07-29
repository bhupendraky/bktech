package com.bktech.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository<E, ID> extends JpaRepository<E, ID> {

}
