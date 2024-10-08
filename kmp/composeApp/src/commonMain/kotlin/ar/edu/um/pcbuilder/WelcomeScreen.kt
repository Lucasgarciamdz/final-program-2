package ar.edu.um.pcbuilder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.um.pcbuilder.builder.DeviceService
import ar.edu.um.pcbuilder.dtos.entities.devices.DeviceDto
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WelcomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val devices = remember { mutableStateOf<List<DeviceDto>?>(null) }
        val errorMessage = remember { mutableStateOf<String?>(null) }
        val fetchDevicesTrigger = remember { mutableStateOf(false) }

        Scaffold(
            topBar = { TopAppBar(title = { Text("Welcome to Computer Builder Shop") }) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navigator.push(LoginScreen()) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { fetchDevicesTrigger.value = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Fetch Devices")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (fetchDevicesTrigger.value) {
                    LaunchedEffect(Unit) {
                        try {
                            val deviceService = DeviceService()
                            val fetchedDevices = withContext(Dispatchers.Default) {
                                deviceService.getDevicesList()
                            }
                            devices.value = fetchedDevices
                        } catch (e: Exception) {
                            errorMessage.value = "Failed to fetch devices: ${e.message}"
                        } finally {
                            fetchDevicesTrigger.value = false
                        }
                    }
                }
                devices.value?.let { deviceList ->
                    deviceList.forEach { device ->
                        device.name?.let { it1 -> Text(text = it1) }
                    }
                }
                errorMessage.value?.let { error ->
                    Text(text = error, color = androidx.compose.ui.graphics.Color.Red)
                }
            }
        }
    }
}