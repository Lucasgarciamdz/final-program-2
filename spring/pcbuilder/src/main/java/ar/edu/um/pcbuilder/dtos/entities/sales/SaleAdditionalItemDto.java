package ar.edu.um.pcbuilder.dtos.entities.sales;


import lombok.Data;

@Data
public class SaleAdditionalItemDto {

  private Long id;
  private Long saleId;
  private Long additionalItemId;
  private Double price;
}