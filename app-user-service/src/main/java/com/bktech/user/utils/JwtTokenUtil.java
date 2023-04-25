package com.bktech.user.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = 24*60*60*1000;

	private static final String SECRET_KEY="743777217A25432A462D4A614E645267556B58703272357538782F413F442847";

	public static String generateToken(String username) {
		long currentTime = System.currentTimeMillis();
		return Jwts.builder().setClaims(new HashMap<>())
				.setSubject(username)
				.setIssuedAt(new Date(currentTime))
				.setExpiration(new Date(currentTime+JWT_TOKEN_VALIDITY))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public static boolean isValidToken(String token, String username) {
		String userNameFromToken = getUsernameFromToken(token);
		return username.equals(userNameFromToken) &&
				!isTokenExpired(token);
	}

	public static String getUsernameFromToken(String token) {
		return getClaimsFromToken(token, Claims::getSubject);
	}

	private static Date getExpirationFromToken(String token) {
		return getClaimsFromToken(token, Claims::getExpiration);
	}

	private static boolean isTokenExpired(String token) {
		return getExpirationFromToken(token).before(new Date());
	}

	private static <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}

	private static Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token).getBody();
	}

	private static Key getKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

}
