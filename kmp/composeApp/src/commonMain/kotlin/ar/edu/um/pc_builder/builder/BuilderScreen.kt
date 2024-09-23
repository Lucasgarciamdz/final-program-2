package ar.edu.um.pc_builder.builder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import pc_builder.composeapp.generated.resources.Res
import pc_builder.composeapp.generated.resources.SCR_20240907_slhu

object AppleShoppingExperienceScreen : Screen {
    @Composable
    override fun Content() {
        AppleShoppingExperience()
    }
}

@Composable
fun AppleShoppingExperience() {
    val navigator = LocalNavigator.currentOrThrow

    // Component selection states
    var currentStep by remember { mutableStateOf(0) }
    var selectedChip by remember { mutableStateOf<ComponentOption?>(null) }
    var selectedMemory by remember { mutableStateOf<ComponentOption?>(null) }
    var selectedStorage by remember { mutableStateOf<ComponentOption?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Centered Image at the Top
        Image(
            painter = painterResource(Res.drawable.SCR_20240907_slhu),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Step-based UI
        when (currentStep) {
            0 -> {
                // Chip Selection Step
                StepContent(
                    title = "Select Chip",
                    options = ChipOptions,
                    selectedOption = selectedChip,
                    onSelectOption = { selectedChip = it },
                    onNext = { currentStep = 1 },
                    onPrevious = null,
                    totalCost = 0
                )
            }
            1 -> {
                // Memory Selection Step
                StepContent(
                    title = "Select Memory",
                    options = MemoryOptions,
                    selectedOption = selectedMemory,
                    onSelectOption = { selectedMemory = it },
                    onNext = { currentStep = 2 },
                    onPrevious = { currentStep = 0 },
                    totalCost = selectedChip?.price?: 0
                )
            }
            2 -> {
                // Storage Selection Step
                StepContent(
                    title = "Select Storage",
                    options = StorageOptions,
                    selectedOption = selectedStorage,
                    onSelectOption = { selectedStorage = it },
                    onNext = {
                        // Navigate to summary screen with the selections
                        navigator.push(SummaryScreen(selectedChip, selectedMemory, selectedStorage))
                    },
                    onPrevious = { currentStep = 1 },
                    totalCost = selectedChip?.price ?: (0 + selectedMemory?.price!!) ?: 0
                )
            }
        }
    }
}

@Composable
fun <T> StepContent(
    title: String,
    options: List<T>,
    selectedOption: T?,
    onSelectOption: (T) -> Unit,
    onNext: () -> Unit,
    onPrevious: (() -> Unit)?
) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // List of options
        options.forEach { option ->
            Button(
                onClick = { onSelectOption(option) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Text(option.toString())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selected Option
        if (selectedOption != null) {
            Text(
                text = "Selected: ${selectedOption}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigation buttons
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            // Back button (only show if onPrevious is provided)
            onPrevious?.let {
                Button(onClick = onPrevious) {
                    Text("Back")
                }
            }

            // Next button
            Button(onClick = onNext) {
                Text("Next")
            }
        }
    }
}
