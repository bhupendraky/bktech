package com.bktech.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bktech.user.constants.Constants;
import com.bktech.user.constants.RoleType;
import com.bktech.user.constants.SecurityType;
import com.bktech.user.entity.Role;
import com.bktech.user.entity.UserEntity;
import com.bktech.user.repository.RoleRepository;
import com.bktech.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppInitConfig implements CommandLineRunner {

	private final UserRepository userRepository;
	private final RoleRepository adminRepository;
	private final PasswordEncoder passwordEncoder;

	@Value("${spring.security.type}")
	private SecurityType securityType;

	@Override
	public void run(String... args) throws Exception {

		// Create very first admin user
		createAdminUser();

	}


	private void createAdminUser() {
		String admin = RoleType.ADMIN.name();
		if (userRepository.existsByUsername(admin)) {
			return;
		}
		String email = admin + "@" + Constants.APP_DOMAIN + ".COM";

		UserEntity adminUser = new UserEntity();
		adminUser.setUsername(admin);
		adminUser.setPassword(passwordEncoder.encode(admin));
		adminUser.setAge(100);
		adminUser.setEmail(email);

		Role role = adminRepository.findByName(admin)
				.orElse(new Role(admin));
		adminUser.getRoles().add(role);

		role = adminRepository.findByName(RoleType.USER.name())
				.orElse(new Role(RoleType.USER.name()));
		adminUser.getRoles().add(role);

		userRepository.save(adminUser);
	}

}
