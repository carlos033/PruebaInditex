package com.inditex.dominio.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inditex.dominio.entidad.Price;

public interface RepositorioPrice extends JpaRepository<Price, Integer> {

  public List<Price> findByBrandIdAndProductIDAndStartDate(String brandId,
      String productID, LocalDateTime fechaInicio);

}
