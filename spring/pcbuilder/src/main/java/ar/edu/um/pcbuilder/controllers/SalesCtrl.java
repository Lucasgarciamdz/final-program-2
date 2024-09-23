package ar.edu.um.pcbuilder.controllers;

import ar.edu.um.pcbuilder.services.SalesSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@Slf4j
public class SalesCtrl {

  SalesSvc salesSvc;

  public SalesCtrl(SalesSvc salesSvc) {
    this.salesSvc = salesSvc;
  }

}
