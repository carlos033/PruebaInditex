package com.inditex.dominio.servicioimpl;

import java.util.Collections;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.inditex.aplicacion.adapter.AdministratorAdapter;
import com.inditex.aplicacion.servicio.JwtUserService;
import com.inditex.infraestructura.entidad.Administrator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JwtUserServiceImpl implements JwtUserService{
	private static final String USERNAME_NOT_FOUND = "User not found with identifier: ";
	private final AdministratorAdapter adapter;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		Administrator administrator = adapter.findById(userName)
				.orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND + userName));
		return new User(administrator.getUsername(), administrator.getPassword(),
				Collections.emptyList());
	}

}
