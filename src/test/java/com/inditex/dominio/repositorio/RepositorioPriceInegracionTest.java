package com.inditex.dominio.repositorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inditex.infraestructura.entidad.Price;

@DataJpaTest
class RepositorioPriceInegracionTest {
  @Autowired
  RepositorioPrice repositorio;

  @Test
  void testObtenerTarifaAplicar() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    OffsetDateTime offsetDateTime = fechaHora.atOffset(ZoneOffset.UTC);

    Optional<Price> result = repositorio.obtenerTarifaAplicar("1", "35455", offsetDateTime);

    assertTrue(result.isPresent());
    assertEquals(35.5, result.get().getPrecio());
    assertEquals("EUR", result.get().getCurr());
  }
}
