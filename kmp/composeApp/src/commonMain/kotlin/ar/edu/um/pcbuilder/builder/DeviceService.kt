package ar.edu.um.pcbuilder.builder

import ar.edu.um.pcbuilder.dtos.entities.devices.DeviceDto
import ar.edu.um.pcbuilder.network.NetworkUtils
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeviceService {
    private val client = NetworkUtils.httpClient
    private val baseUrl = "http://localhost:8080/pcbuilder"

    suspend fun getDevicesList(): List<DeviceDto> {
        return withContext(Dispatchers.Default) {
            client.get("$baseUrl/devices").body()
        }
    }
}