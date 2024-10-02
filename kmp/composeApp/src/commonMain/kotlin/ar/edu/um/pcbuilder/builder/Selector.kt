package ar.edu.um.pcbuilder.builder

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
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import pc_builder.composeapp.generated.resources.Res
import pc_builder.composeapp.generated.resources.SCR_20240907_slhu

@Composable
fun StepContent(
    title: String,
    options: List<ComponentOption>,
    selectedOption: ComponentOption?,
    onSelectOption: (ComponentOption) -> Unit,
    onNext: () -> Unit,
    onPrevious: (() -> Unit)?,
    totalCost: Int // New parameter for total cost
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = MaterialTheme.typography.h4)

        Spacer(modifier = Modifier.weight(1f))

        // Botones para seleccionar opciones
        options.forEach { option ->
            OptionCard(
                option = option,
                isSelected = option == selectedOption,
                onClick = { onSelectOption(option) }
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(2f))

        // Botones para volver o pasar a la siguiente
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Botón para volver, solo si es posible volver
            if (onPrevious != null) {
                Button(onClick = onPrevious, modifier = Modifier.weight(1f)) {
                    Text("Back")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Display the total cost
            TotalCostDisplay(totalCost)

            Spacer(modifier = Modifier.weight(1f))

            // Botón para pasar a la siguiente, solo si se ha seleccionado una opción
            Button(
                onClick = onNext,
                enabled = selectedOption != null,
                modifier = Modifier.weight(1f)
            ) {
                Text("Next")
            }
        }
        Spacer(modifier = Modifier.weight(2f))
    }
}

@Composable
fun TotalCostDisplay(totalCost: Int) {
    Text(
        text = "Total Cost: $${totalCost / 100}.${totalCost % 100}",
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
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
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onClick),
        elevation = if (isSelected) 12.dp else 4.dp,
        shape = MaterialTheme.shapes.medium,
        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colors.primary) else null,
        backgroundColor = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.1f) else MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.SCR_20240907_slhu),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = option.name, style = MaterialTheme.typography.h6)
                Text(
                    text = if (option.price == 0) "Included" else "+$${option.price / 100}.${option.price % 100}",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}