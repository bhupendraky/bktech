package com.bky.user.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bky.user.entity.User;

@Repository
@Transactional
public class UserEntityManager {

	@PersistenceContext
	private EntityManager manager;

	public List<User> findAll() {
		TypedQuery<User> findAllQuery = manager.createNamedQuery("findAll", User.class);
		return findAllQuery.getResultList();
	}

	public User findById(Long id) {
		return manager.find(User.class, id);
	}

	public User save(User user) {
		return manager.merge(user);
	}

	public User deleteById(Long id) {
		User user = findById(id);
		manager.remove(user);
		return user;
	}

}
