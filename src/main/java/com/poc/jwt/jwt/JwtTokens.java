package com.poc.jwt.jwt;

import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Hello world!
 *
 */
public class JwtTokens {
	private static final String DEFAULT_SECRET = "secret123";

	public static void main(String[] args) {
		String token = generateToken("FirstClaim");
		boolean tokenVerify = verifyToken(token);
		String getClaim = getClaim(token);
		System.out.println("token generated is: "+token+"\n"+"token verification is: "+tokenVerify+"\n"+"claim is: "+getClaim);
	}
	
	private static String generateToken(String claim1){
		
		Algorithm algorithm = Algorithm.HMAC256(DEFAULT_SECRET);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 1); // making token valid for one hour.
		Date expiresat = cal.getTime();   // setting expiry time param
		
		String token = JWT.create().withIssuer("auth0").withExpiresAt(expiresat).withClaim("key", claim1).sign(algorithm);
		return token;
	}
	
	private static boolean verifyToken(String token){
		
		try{
			Algorithm algorithm = Algorithm.HMAC256(DEFAULT_SECRET);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
			DecodedJWT jwt = verifier.verify(token); // if the verification fails it will throw an exception
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
  private static String getClaim(String token){
		
		Algorithm algorithm = Algorithm.HMAC256(DEFAULT_SECRET);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("key").asString();
		
	}
}
