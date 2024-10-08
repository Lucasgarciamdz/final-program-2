package ar.edu.um.pcbuilder.controllers;

import ar.edu.um.pcbuilder.dtos.entities.devices.DeviceDto;
import ar.edu.um.pcbuilder.services.DevicesSvc;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
@Slf4j
public class DevicesCtrl {

  DevicesSvc devicesSvc;

  public DevicesCtrl(DevicesSvc devicesSvc) {
    this.devicesSvc = devicesSvc;
  }
  
  @GetMapping
  public List<DeviceDto> getDevices() {
    return devicesSvc.getDevices();
  }

}
