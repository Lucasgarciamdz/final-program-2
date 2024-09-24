package ar.edu.um.pcbuilder.services;

import ar.edu.um.pcbuilder.properties.WharehousePpties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ComponentsSvc extends BaseSvc {

  public ComponentsSvc(WharehousePpties wharehousePpties) {
    super(wharehousePpties);
  }
}
