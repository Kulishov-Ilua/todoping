package ru.pingfly.todoping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompactProfile( Color(7,7,7), Color.White, Color(122,122,122), Color(169,11,238))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}