package ar.edu.um.pcbuilder.dtos.entities.devices;

import lombok.Data;

@Data
public class DevicePersonalizationDto {

  private Long id;
  private Long deviceId;
  private String name;
  private String description;
}
