package ru.pingfly.todoping

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "todoping",
    ) {
        App()
    }
}