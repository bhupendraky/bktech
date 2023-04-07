package com.bktech.user.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDetailConfig {

	@Bean
	@ConfigurationProperties(prefix = "user.detail.bhupen")
	public UserDetail getBhupen() {
		return new UserDetail();
	}

	@Bean
	@ConfigurationProperties(prefix = "user.detail.rakesh")
	public UserDetail getRakesh() {
		return new UserDetail();
	}

	public class UserDetail {
		private String name;
		private String address;
		private Map<String, Integer> phone;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Map<String, Integer> getPhone() {
			return phone;
		}

		public void setPhone(Map<String, Integer> phone) {
			this.phone = phone;
		}

		@Override
		public String toString() {
			return "UserDetail [name=" + name + ", address=" + address + ", phone=" + phone + "]";
		}

	}

}
