package ar.edu.um.pcbuilder.dtos.entities.devices

data class DeviceAdditionalItemDto(
    val id: Long?,
    val deviceId: Long?,
    val name: String?,
    val description: String?,
    val price: Double?,
    val freePrice: Double?
)