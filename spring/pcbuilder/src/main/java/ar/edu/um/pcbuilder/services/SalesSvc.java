package ar.edu.um.pcbuilder.services;

import ar.edu.um.pcbuilder.properties.WarehousePpties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SalesSvc extends BaseSvc {

  public SalesSvc(WarehousePpties warehousePpties) {
    super(warehousePpties);
  }
}
