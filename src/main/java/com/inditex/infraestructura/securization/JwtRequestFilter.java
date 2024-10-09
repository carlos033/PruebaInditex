package com.inditex.infraestructura.securization;

import com.inditex.dominio.servicioimpl.JwtUserServiceImpl;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter{
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer";
	private JwtToken jwtTokenUtil;
	private ApplicationContext applicationContext;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws ServletException, IOException, MalformedJwtException {
		final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
		String identifier = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER_PREFIX)) {
			jwtToken = requestTokenHeader.substring(7);
			identifier = jwtTokenUtil.getTokenIdentifier(jwtToken);

		}

		if (shouldAuthenticate(identifier, jwtToken)) {
			authenticateAndSetSecurityContext(request, identifier, jwtToken);
		}

		chain.doFilter(request, response);
	}

	private boolean shouldAuthenticate(String identifier, String jwtToken) {
		return identifier != null && SecurityContextHolder.getContext().getAuthentication() == null
				&& jwtTokenUtil.validateToken(jwtToken, identifier);
	}

	private void authenticateAndSetSecurityContext(HttpServletRequest request, String identifier,
			String jwtToken) {
		JwtUserServiceImpl jwtUserDetailsService = applicationContext.getBean(JwtUserServiceImpl.class);
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(identifier);

		if (isTokenValid(jwtToken, identifier)) {
			setAuthenticationInSecurityContext(request, userDetails);
		}
	}

	private boolean isTokenValid(String jwtToken, String identifier) {
		return jwtTokenUtil.validateToken(jwtToken, identifier);
	}

	private void setAuthenticationInSecurityContext(HttpServletRequest request,
			UserDetails userDetails) {
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

}
