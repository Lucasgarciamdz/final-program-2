package ar.edu.um.pcbuilder.controllers;

import ar.edu.um.pcbuilder.services.ComponentsSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/components")
@Slf4j
public class ComponentsCtrl {

  ComponentsSvc componentsSvc;

  public ComponentsCtrl(ComponentsSvc componentsSvc) {
    this.componentsSvc = componentsSvc;
  }

}
