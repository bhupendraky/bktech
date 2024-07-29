package com.bktech.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bktech.infra.constants.Globals;
import com.bktech.user.constants.RoleType;
import com.bktech.user.entity.UserEntity;
import com.bktech.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Configuration
public class AppInitConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		// Create very first admin user
		createAdminUser();

	}


	@Transactional
	public void createAdminUser() {
		String admin = RoleType.ADMIN.name();
		if (userRepository.existsByUsername(admin)) {
			return;
		}
		String email = admin + "@" + Globals.APP_DOMAIN + ".COM";

		UserEntity adminUser = new UserEntity();
		adminUser.setUsername(admin);
		adminUser.setPassword(passwordEncoder.encode(admin));
		adminUser.setAge(100);
		adminUser.setEmail(email);
		adminUser.setRole(admin);
		userRepository.save(adminUser);
	}

}
