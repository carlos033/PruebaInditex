package com.inditex.aplicacion.adapter;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.inditex.dominio.repositorio.AdministratorRepository;
import com.inditex.infraestructura.entidad.Administrator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AdministratorAdapter{
	private final AdministratorRepository userRepository;

	public Optional<Administrator> findById(String user) {
		return userRepository.findById(user);
	}
}
