package com.inditex.dominio.serviceImpl;

import java.time.OffsetDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inditex.aplicacion.servicio.ServicioPrice;
import com.inditex.dominio.excepciones.NotContentInditex;
import com.inditex.dominio.repositorio.RepositorioPrice;
import com.inditex.infraestructura.entidad.Price;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ServicioImplPrice implements ServicioPrice{
	private static final Logger logger = LoggerFactory.getLogger(ServicioImplPrice.class);

	private final RepositorioPrice repositorioPrice;

	@Override
	public Price obtenerTarifaAplicar(String idEmpresa, String productId,
			OffsetDateTime fechaAConsulta) {
		logger.info("Hacemos la llamada al repositorio");

		return repositorioPrice.obtenerTarifaAplicar(idEmpresa, productId, fechaAConsulta).orElseThrow(
				() -> new NotContentInditex(HttpStatus.NO_CONTENT, "Tarifa no encontrada en la BD"));
	}
}
