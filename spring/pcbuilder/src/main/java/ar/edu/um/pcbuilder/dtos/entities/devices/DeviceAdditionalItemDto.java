package ar.edu.um.pcbuilder.dtos.entities.devices;

import lombok.Data;

@Data
public class DeviceAdditionalItemDto {

  private Long id;
  private Long deviceId;
  private String name;
  private String description;
  private Double price;
  private Double freePrice;
}