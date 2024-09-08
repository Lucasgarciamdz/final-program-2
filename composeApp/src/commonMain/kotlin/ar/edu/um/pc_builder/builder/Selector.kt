package ar.edu.um.pc_builder.builder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import pc_builder.composeapp.generated.resources.Res
import pc_builder.composeapp.generated.resources.SCR_20240907_slhu
import pc_builder.composeapp.generated.resources.compose_multiplatform

@Composable
fun StepContent(
    title: String,
    options: List<ComponentOption>,
    selectedOption: ComponentOption?,
    onSelectOption: (ComponentOption) -> Unit,
    onNext: () -> Unit,
    onPrevious: (() -> Unit)?
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = MaterialTheme.typography.h2)

        Spacer(modifier = Modifier.weight(1f))

        // Attractive option cards instead of a list
        options.forEach { option ->
            OptionCard(
                option = option,
                isSelected = option == selectedOption,
                onClick = { onSelectOption(option) }
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(2f))

        // Navigation Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (onPrevious != null) {
                Button(onClick = onPrevious, modifier = Modifier.weight(1f)) {
                    Text("Back")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onNext,
                enabled = selectedOption != null, // Only enable if an option is selected
                modifier = Modifier.weight(1f)
            ) {
                Text("Next")
            }
        }
        Spacer(modifier = Modifier.weight(2f))
    }
}

@Composable
fun OptionCard(
    option: ComponentOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        elevation = if (isSelected) 8.dp else 4.dp,
        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colors.primary) else null,
        backgroundColor = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.1f) else MaterialTheme.colors.surface
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            // Placeholder for the option image, use Coil or any image loading library
            Image(
                painter = painterResource(Res.drawable.SCR_20240907_slhu),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = option.name, style = MaterialTheme.typography.body1)
                Text(
                    text = if (option.price == 0) "Included" else "+$${option.price / 100}.${option.price % 100}",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}