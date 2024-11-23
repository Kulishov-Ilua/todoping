package ru.pingfly.todoping

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {


        val userAgent = window.navigator.userAgent.lowercase()
        if(userAgent.contains("mobile")||userAgent.contains("android")||userAgent.contains("iphone")){
            val server = Reuests()
            val scope = rememberCoroutineScope()
            scope.launch {
                server.auth(onSuccess = {key->
                    token=key.accessToken

                }, onFailure = {error -> println(error) })
            }
            CompactProfile( Color(7,7,7), Color.White, Color(122,122,122), Color(169,11,238))
            when(appState){
            }
        }else{
            App()
        }

    }
}