package br.com.glauber.circulusplay.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String geraToken(String usuario) {
		return Jwts.builder()
				.setSubject(usuario)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}


	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			String nomeUsuario = claims.getSubject();
			Date dataExpiracao = claims.getExpiration();
			Date agora = new Date(System.currentTimeMillis());
			if (nomeUsuario != null && dataExpiracao != null && agora.before(dataExpiracao)) {
				return true;
			}
		}
		return false;
	}


	private Claims getClaims(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(secret.getBytes())
					.parseClaimsJws(token)
					.getBody();			
		}
		catch(Exception e) {
			return null;
		}
	}


	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}
}