package ar.edu.um.pc_builder

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "pc-builder",
    ) {
        App()
    }
}