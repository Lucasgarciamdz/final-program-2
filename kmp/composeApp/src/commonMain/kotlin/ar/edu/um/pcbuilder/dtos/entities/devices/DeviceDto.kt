package ar.edu.um.pcbuilder.dtos.entities.devices

data class DeviceDto(
    val id: Long?,
    val code: String?,
    val name: String?,
    val description: String?,
    val basePrice: Double?,
    val currency: String?,
    val characteristics: List<DeviceCharacteristicDto>?,
    val personalizations: List<DevicePersonalizationDto>?,
    val options: List<DeviceOptionDto>?,
    val additionalItems: List<DeviceAdditionalItemDto>?
)