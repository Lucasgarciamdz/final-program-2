package ar.edu.um.pcbuilder.dtos.entities.sales

import kotlinx.datetime.Instant

data class SaleDto(
    val id: Long?,
    val userId: Long?,
    val deviceId: Long?,
    val finalPrice: Double?,
    val saleDate: Instant?,
    val personalizations: List<SalePersonalizationDto>?,
    val additionalItems: List<SaleAdditionalItemDto>?
)