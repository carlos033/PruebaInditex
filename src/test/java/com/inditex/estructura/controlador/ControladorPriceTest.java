package com.inditex.estructura.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inditex.aplicacion.dto.PriceDTO;
import com.inditex.aplicacion.mapper.MapperPrice;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.NotContentInditex;
import com.inditex.dominio.servicioimpl.ServicioImplPrice;
import com.inditex.infraestructura.controlador.ControladorPrice;

@ExtendWith(MockitoExtension.class)
class ControladorPriceTest {
  @InjectMocks
  private ControladorPrice controlador;

  @Mock
  private ServicioImplPrice servicio;

  @Spy
  private MapperPrice mapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testListarPrices() throws NotContentInditex {
    PriceDTO productoDTO = new PriceDTO();
    when(servicio.obtenerTarifaAplicar(any(), any(), any(OffsetDateTime.class)))
        .thenReturn(new Price());

    doReturn(productoDTO).when(mapper).mapeoADTO(any(Price.class), any(OffsetDateTime.class));

    ResponseEntity<PriceDTO> response =
        controlador.obtenerTarifaAplicar("empresaId", "productoId", OffsetDateTime.now());

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    verify(servicio, times(1)).obtenerTarifaAplicar(eq("empresaId"), eq("productoId"),
        any(OffsetDateTime.class));
    verify(mapper, times(1)).mapeoADTO(any(Price.class), any(OffsetDateTime.class));
    assertEquals(productoDTO, response.getBody());
  }
}
