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
            //CardsEventDesktop(Color.Gray, Color.White, "Название", "01.01.2024", "20:20", "Г-432")
            //CardsTaskPhone(Color.Gray, Color.White, "Название", "не начато", "11.11.24", "Важно")
            //CardsTaskDesktop(Color.Gray, Color.White, "Название", "не начато", "11.11.24", "Важно")
            CardsEventPhone(Color.Gray, Color.White, "Название", "01.01.2024", "20:20", "Г-432")
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}