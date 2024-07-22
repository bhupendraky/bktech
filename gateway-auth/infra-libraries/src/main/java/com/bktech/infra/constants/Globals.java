package com.bktech.infra.constants;

public interface Globals {

	String REQ_HEADER_USER_ID = "userId";
	String SUCCESS = "SUCCESS";
	String APP_DOMAIN="BKTECH";
	interface BasicAuth {
		interface Prefix {
			String TEXT = "Basic ";
			int LENGTH = 6;
		}
	}

	interface JwtAuth {
		interface Prefix {
			String TEXT = "Bearer ";
			int LENGTH = 7;
		}
	}

}
