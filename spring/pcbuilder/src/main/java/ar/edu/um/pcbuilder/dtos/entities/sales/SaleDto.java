package ar.edu.um.pcbuilder.dtos.entities.sales;

import java.sql.Timestamp;
import java.util.List;
import lombok.Data;

@Data
public class SaleDto {

  private Long id;
  private Long userId;
  private Long deviceId;
  private Double finalPrice;
  private Timestamp saleDate;
  private List<SalePersonalizationDto> personalizations;
  private List<SaleAdditionalItemDto> additionalItems;
}