package com.bktech.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bktech.user.constants.Constants;
import com.bktech.user.constants.RoleType;
import com.bktech.user.data.RoleRepository;
import com.bktech.user.data.UserRepository;
import com.bktech.user.domain.Role;
import com.bktech.user.domain.UserEntity;
import com.google.common.collect.Lists;
import com.spring4all.swagger.SwaggerProperties;
import com.spring4all.swagger.SwaggerProperties.GlobalOperationParameter;

@Configuration
public class AppInitConfig implements CommandLineRunner {

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		// Swagger setup
		configureSwaggerHeader();

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


	private void configureSwaggerHeader() throws Exception {
		GlobalOperationParameter userId = new GlobalOperationParameter();
		userId.setName(Constants.REQ_HEADER_USER_ID);
		userId.setDescription("User ID");
		userId.setParameterType("header");
		userId.setModelRef("string");
		userId.setRequired("true");

		if(swaggerProperties.getGlobalOperationParameters() == null){
			swaggerProperties.setGlobalOperationParameters(Lists.newArrayList(userId));
		} else {
			swaggerProperties.getGlobalOperationParameters().add(userId);
		}
	}


}
