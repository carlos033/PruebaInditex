package com.inditex.dominio.repositorio;

import com.inditex.infraestructura.entidad.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface RepositorioPrice extends JpaRepository<Price, Integer> {

	@Query("SELECT p FROM Price p WHERE p.brandId = :brandId AND p.productId = :productId "
			+ "AND :fecha BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC, p.startDate DESC LIMIT 1")
	Optional<Price> obtenerTarifaAplicar(@Param("brandId") String brandId, @Param("productId") String productID,
			@Param("fecha") OffsetDateTime fecha);

}
