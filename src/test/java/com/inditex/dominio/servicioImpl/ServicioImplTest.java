package com.inditex.dominio.servicioImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.ExcepcionInditex;
import com.inditex.dominio.repositorio.RepositorioPrice;

@ExtendWith(MockitoExtension.class)
class ServicioImplTest {

  @InjectMocks
  private ServicioImplPrice servicio;

  @Mock
  private RepositorioPrice repositorio;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testListarPrices() throws ExcepcionInditex {
    String idEmpresa = "2";
    String idProducto = "2";
    LocalDateTime fechaInicio = LocalDateTime.now();

    // Crear al menos un producto en la lista
    Price producto = new Price();
    producto.setId(1);
    producto.setBrandId(idEmpresa);
    producto.setStartDate(fechaInicio);
    producto.setEndDate(fechaInicio);
    producto.setPriceList("2");
    producto.setProductID(idProducto);
    producto.setPriority((short) 1);
    producto.setPrecio(22.0);
    producto.setCurr("EUR");
    List<Price> listaPrices = new ArrayList<>();
    listaPrices.add(producto); // Agregar el producto a la lista

    when(repositorio.findByBrandIdAndProductIDAndStartDate(idEmpresa,
        idProducto, fechaInicio)).thenReturn(listaPrices);

    List<Price> resultado =
        servicio.listarPrices(idEmpresa, idProducto, fechaInicio);

    verify(repositorio).findByBrandIdAndProductIDAndStartDate(idEmpresa,
        idProducto, fechaInicio);

    assertEquals(listaPrices, resultado);
  }

  @Test
  void testListarPrices2() throws ExcepcionInditex {
    String idEmpresa = "2";
    String idProducto = "2";
    LocalDateTime fechaInicio = LocalDateTime.now();
    Price producto = new Price(1, idEmpresa, fechaInicio, fechaInicio, "2",
        idProducto, (short) 1, 22.0, "EUR");


    List<Price> listaPrices = new ArrayList<>();
    listaPrices.add(producto); // Agregar el producto a la lista

    when(repositorio.findByBrandIdAndProductIDAndStartDate(idEmpresa,
        idProducto, fechaInicio)).thenReturn(listaPrices);

    List<Price> resultado =
        servicio.listarPrices(idEmpresa, idProducto, fechaInicio);

    verify(repositorio).findByBrandIdAndProductIDAndStartDate(idEmpresa,
        idProducto, fechaInicio);

    assertEquals(listaPrices, resultado);
  }

  @Test
  void testListarPricesNoEncontrado() {
    String idEmpresa = "empresaId";
    String idProducto = "productoId";
    LocalDateTime fechaInicio = LocalDateTime.now();

    assertThrows(ExcepcionInditex.class,
        () -> servicio.listarPrices(idEmpresa, idProducto, fechaInicio));
  }
}
