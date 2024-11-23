package ru.pingfly.todoping

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val userAgent = window.navigator.userAgent.lowercase()
        if(userAgent.contains("mobile")||userAgent.contains("android")||userAgent.contains("iphone")){
            when(appState){

            }
        }else{
            App()
        }

    }
}