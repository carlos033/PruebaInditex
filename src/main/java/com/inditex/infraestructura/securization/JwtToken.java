package com.inditex.infraestructura.securization;

import com.inditex.dominio.excepciones.MyInvalidClaimException;
import com.inditex.dominio.excepciones.TokenValidationException;
import com.inditex.infraestructura.entidad.Administrator;
import com.inditex.infraestructura.entidad.Logable;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@Component
public class JwtToken{
	private static final String USER_NAME = "userName";
	private static final String INVALID_CLAIM_EXCEPTION =
			"The 'user' object is not of the expected type";
	private static final String USER = "user";
	private SecretKey secret;

	public JwtToken() {
		this.secret = generateRandomSecret();
	}

	private SecretKey generateRandomSecret() {
		byte[] randomBytes = new byte[256];
		new SecureRandom().nextBytes(randomBytes);
		byte[] keyBytes = Base64.getEncoder().encode(randomBytes);
		return new SecretKeySpec(keyBytes, "HmacSHA256");
	}

	public String getTokenIdentifier(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getTokenExpiration(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();
	}

	private Boolean hasTokenExpired(String token) {
		final Date expiration = getTokenExpiration(token);
		return expiration.before(new Date());
	}

	public String generateToken(Logable user) {
		final Map<String, Object> claims = new HashMap<>();
		final Map<String, Object> userMap = new HashMap<>();
		userMap.put(USER, user.getIdentifier());
		userMap.put(USER_NAME, ((Administrator) user).getName());
		claims.put(USER, new HashMap<>(userMap));
		String subject = ((Administrator) user).getIdentifier();
		return doGenerateToken(claims, subject);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Instant now = Instant.now();
		final Instant expirationTime = now.plus(Duration.ofDays(1));

		Object usuarioClaimsObj = claims.get(USER);

		if (usuarioClaimsObj instanceof Map) {
			Map<?, ?> rawUsuarioClaims = (Map<?, ?>) usuarioClaimsObj;
			Map<String, Object> usuarioClaims = new HashMap<>();

			for (Map.Entry<?, ?> entry : rawUsuarioClaims.entrySet()) {
				if (entry.getKey() instanceof String) {
					String key = (String) entry.getKey();
					usuarioClaims.put(key, entry.getValue());
				}
			}

			if (!usuarioClaims.isEmpty()) {

				return Jwts.builder().subject(subject).issuedAt(Date.from(now))
						.expiration(Date.from(expirationTime)).claim(USER, usuarioClaims).signWith(secret)
						.compact();
			}
		}
		throw new MyInvalidClaimException(INVALID_CLAIM_EXCEPTION);
	}


	public Boolean validateToken(String token, String expectedSubject)
			throws TokenValidationException {
		final String identifier = getTokenIdentifier(token);
		return (identifier.equals(expectedSubject) && !hasTokenExpired(token));
	}
}
