package ar.edu.um.pcbuilder.dtos.entities.devices;


import lombok.Data;

@Data
public class DeviceCharacteristicDto {

  private Long id;
  private Long deviceId;
  private String name;
  private String description;
}