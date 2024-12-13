package com.product.catalog.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
@ComponentScan({ "com.product" })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

//		boolean validToken = false;
//
//		String secretKey = "BvPHGM8C0ia4uOuxxqPD5DTbWC9F9TWvPStp3pb7ARo0oK2mJ3pd3YG4lxA9i8bj6OTbadwezxgeEByY";
//		Date currentDate = new Date();
//		Long validDuration = (long) (5 * 60 * 1000);
//		Date validity = new Date(currentDate.getTime() + validDuration);
//
//		Map<String, Object> claims = new HashMap<String, Object>();
//		claims.put("userName", "Prasanth");
//		claims.put("action", "login");
//
//		String token = Jwts.builder().setClaims(claims).setSubject("Prasanth").setIssuedAt(currentDate)
//				.setExpiration(validity).signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
//
//		Jws<Claims> jwtClaims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
//
//		Claims body = jwtClaims.getBody();
//		System.out.println("Subject: " + body.getSubject());
//		System.out.println("Expiration: " + body.getExpiration());
//
//		if (body.getExpiration().before(new Date())) {
//			validToken = false;
//		} else {
//			validToken = true;
//		}
	}

}
