package com.product.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationService {

	private static final Log LOG = LogFactory.getLog(JWTAuthenticationService.class);

	String secretKey = "BvPHGM8C0ia4uOuxxqPD5DTbWC9F9TWvPStp3pb7ARo0oK2mJ3pd3YG4lxA9i8bj6OTbadwezxgeEByY";

	public String generateToken(String userName) {

		String token = "";

		try {

			if (!userName.isEmpty()) {

				Date currentDate = new Date();
				Long validDuration = (long) (5 * 60 * 1000);
				Date validity = new Date(currentDate.getTime() + validDuration);

				Map<String, Object> claims = new HashMap<String, Object>();
				claims.put("userName", userName);
				claims.put("action", "login");

				token = Jwts.builder().setClaims(claims).setSubject(token).setIssuedAt(currentDate)
						.setExpiration(validity).signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();

				return token;
			}
		} catch (Exception e) {
			LOG.error("ERROR @generateToken =>" + e.getMessage(), e);
		}
		return token;
	}

	public boolean validateToken(String jwtToken) {

		boolean validToken = false;

		try {

			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build()
					.parseClaimsJws(jwtToken);

			Claims body = claims.getBody();
			System.out.println("Subject: " + body.getSubject());
			System.out.println("Expiration: " + body.getExpiration());

			if (body.getExpiration().before(new Date())) {
				validToken = false;
			} else {
				validToken = true;
			}

		} catch (Exception e) {
			LOG.error("ERROR @validateToken =>" + e.getMessage(), e);
		}
		return validToken;
	}

}
