package com.inditex.presentacion.controlador;

import java.util.Collection;
import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.aplicacion.dto.JwtRequest;
import com.inditex.aplicacion.dto.JwtResponse;
import com.inditex.aplicacion.servicio.AdministratorService;
import com.inditex.infraestructura.securization.JwtToken;
import com.inditex.presentacion.api.AutenticacionApi;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */
@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin
public class AutenticacionController implements AutenticacionApi{
	private final AuthenticationManager authenticationManager;
	private final JwtToken jwtToken;
	private final AdministratorService administratorService;

	@Override
	public ResponseEntity<JwtResponse> autenticacionLoginPost(@Valid JwtRequest jwtRequest) {
		authenticate(jwtRequest.getIdentificator(), jwtRequest.getPassword(), Collections.emptyList());
		String token = generateToken(jwtRequest.getIdentificator());
		return ResponseEntity.ok(JwtResponse.builder().jwttoken(token).build());
	}

	private String generateToken(String identificador) {
		log.info("generate token");
		return jwtToken.generateToken(administratorService.findById(identificador));
	}

	private void authenticate(String username, String password,
			Collection<? extends GrantedAuthority> authorities) throws BadCredentialsException{
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password, authorities));
	}
}
