package ru.pingfly.todoping

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val server = Reuests()
            val scope = rememberCoroutineScope()
            scope.launch {
                server.auth(onSuccess = {key->
                    token=key.accessToken

                }, onFailure = {error -> println(error) })
            }
            //CompactProfile( Color(7,7,7), Color.White, Color(122,122,122), Color(169,11,238))
            phoneScreen(Color(7,7,7), Color.White, Color(122,122,122), Color(169,11,238))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}