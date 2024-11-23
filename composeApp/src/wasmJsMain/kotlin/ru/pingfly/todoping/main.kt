package ru.pingfly.todoping

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        createSpaceDesctop(
            Color(32,32,32), Color.White, Color(122,122,122), Color(169,11,238),
            onCreate ={}, onClose = {})
    }
}