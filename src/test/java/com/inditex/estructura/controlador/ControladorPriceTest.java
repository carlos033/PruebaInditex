package com.inditex.estructura.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
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
import com.inditex.dominio.excepciones.ExcepcionInditex;
import com.inditex.dominio.servicioImpl.ServicioImplPrice;
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
  void testListarPrices() throws ExcepcionInditex {
    PriceDTO productoDTO = new PriceDTO();
    when(servicio.listarPrices(any(), any(), any(LocalDateTime.class)))
        .thenReturn(Collections.singletonList(new Price()));

    doReturn(productoDTO).when(mapper).mapeoADTO(any(Price.class));

    ResponseEntity<List<PriceDTO>> response = controlador
        .listarPrices("empresaId", "productoId", LocalDateTime.now());

    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertFalse(response.getBody().isEmpty());
  }
}
