package ar.edu.um.pcbuilder.dtos.entities.sales

data class SaleAdditionalItemDto(
    val id: Long?,
    val saleId: Long?,
    val additionalItemId: Long?,
    val price: Double?
)