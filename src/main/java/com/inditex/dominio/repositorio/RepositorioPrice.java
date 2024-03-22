package com.inditex.dominio.repositorio;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.inditex.infraestructura.entidad.Price;

public interface RepositorioPrice extends JpaRepository<Price, Integer> {

	@Query("SELECT p FROM Price p WHERE p.brandId = :brandId AND p.productID = :productID "
			+ "AND :fecha BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC, p.startDate DESC LIMIT 1")
	Optional<Price> obtenerTarifaAplicar(@Param("brandId") String brandId, @Param("productID") String productID,
			@Param("fecha") OffsetDateTime fecha);

}
