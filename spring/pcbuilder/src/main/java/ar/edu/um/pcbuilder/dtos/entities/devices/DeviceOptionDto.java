package ar.edu.um.pcbuilder.dtos.entities.devices;

import lombok.Data;

@Data
public class DeviceOptionDto {

  private Long id;
  private Long personalizationId;
  private String code;
  private String name;
  private String description;
  private Double additionalPrice;
}