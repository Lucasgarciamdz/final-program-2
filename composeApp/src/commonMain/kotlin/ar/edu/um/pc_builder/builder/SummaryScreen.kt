package ar.edu.um.pc_builder.builder

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


class SummaryScreen(
    private val selectedChip: ComponentOption?,
    private val selectedMemory: ComponentOption?,
    private val selectedStorage: ComponentOption?
) : Screen {
    @Composable
    override fun Content() {
        Summary(selectedChip, selectedMemory, selectedStorage)
    }
}


@Composable
fun Summary(
    selectedChip: ComponentOption?,
    selectedMemory: ComponentOption?,
    selectedStorage: ComponentOption?
) {
    val navigator = LocalNavigator.currentOrThrow

    // Display the summary of selections
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Summary", style = MaterialTheme.typography.h2)

        Spacer(modifier = Modifier.height(24.dp))

        // Display selected options
        Text("Chip: ${selectedChip?.name ?: "None"}")
        Text("Memory: ${selectedMemory?.name ?: "None"}")
        Text("Storage: ${selectedStorage?.name ?: "None"}")

        Spacer(modifier = Modifier.height(16.dp))

        // Calculate the total price
        val basePrice = 109900
        val totalPrice = (basePrice +
                (selectedChip?.price ?: 0) +
                (selectedMemory?.price ?: 0) +
                (selectedStorage?.price ?: 0))
        Text("Total Price: $${totalPrice / 100}.${totalPrice % 100}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navigator.pop() }) {
            Text("Back to Shop")
        }
    }
}
