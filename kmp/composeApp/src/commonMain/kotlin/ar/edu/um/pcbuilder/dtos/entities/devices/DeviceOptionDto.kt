package ar.edu.um.pcbuilder.dtos.entities.devices

data class DeviceOptionDto(
    val id: Long?,
    val personalizationId: Long?,
    val code: String?,
    val name: String?,
    val description: String?,
    val additionalPrice: Double?
)