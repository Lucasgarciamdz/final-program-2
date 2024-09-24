package ar.edu.um.pcbuilder.dtos.entities.sales;

import lombok.Data;

@Data
public class SalePersonalizationDto {

  private Long id;
  private Long saleId;
  private Long personalizationOptionId;
  private Double price;
}