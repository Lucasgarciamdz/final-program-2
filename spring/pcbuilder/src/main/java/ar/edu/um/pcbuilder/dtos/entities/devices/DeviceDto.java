package ar.edu.um.pcbuilder.dtos.entities.devices;

import java.util.List;
import lombok.Data;

@Data
public class DeviceDto {

  private Long id;
  private String code;
  private String name;
  private String description;
  private Double basePrice;
  private String currency;
  private List<DeviceCharacteristicDto> characteristics;
  private List<DevicePersonalizationDto> personalizations;
  private List<DeviceOptionDto> options;
  private List<DeviceAdditionalItemDto> additionalItems;
}
