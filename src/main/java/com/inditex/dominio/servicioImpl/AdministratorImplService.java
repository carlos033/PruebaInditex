package com.inditex.dominio.servicioimpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inditex.aplicacion.adapter.AdministratorAdapter;
import com.inditex.aplicacion.servicio.AdministratorService;
import com.inditex.dominio.excepciones.NotContentInditex;
import com.inditex.infraestructura.entidad.Administrator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Carlos Diaz https://github.com/carlos033?tab=repositories
 */

@AllArgsConstructor
@Service
@Slf4j
public class AdministratorImplService implements AdministratorService{
	private static final String NO_CONTENT_MESSAGE = "User not found in DB";
	private final AdministratorAdapter adapter;

	@Override
	public Administrator findById(String usuario) {
		return adapter.findById(usuario).orElseThrow(() -> {
			log.info(NO_CONTENT_MESSAGE);
			return new NotContentInditex(HttpStatus.NO_CONTENT, NO_CONTENT_MESSAGE);
		});
	}
}
