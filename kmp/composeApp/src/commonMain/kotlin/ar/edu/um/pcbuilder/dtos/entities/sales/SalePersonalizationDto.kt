package ar.edu.um.pcbuilder.dtos.entities.sales

data class SalePersonalizationDto(
    val id: Long?,
    val saleId: Long?,
    val personalizationOptionId: Long?,
    val price: Double?
)