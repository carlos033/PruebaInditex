package com.inditex.dominio.repositorio;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.inditex.dominio.entidad.Price;

public interface RepositorioPrice extends JpaRepository<Price, Integer> {

  @Query("""
      SELECT p FROM Price p \
      WHERE p.brandId = :brandId \
      AND p.productID = :productID \
      AND :fecha BETWEEN p.startDate AND p.endDate \
      AND p.priceList = (SELECT MAX(pp.priceList) FROM Price pp \
      WHERE pp.brandId = :brandId \
      AND pp.productID = :productID \
      AND :fecha BETWEEN pp.startDate AND pp.endDate)\
      """)
  Optional<Price> findPricesWithMaxPriceList(@Param("brandId") String brandId,
      @Param("productID") String productID,
      @Param("fecha") OffsetDateTime fecha);

}
