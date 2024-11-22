package ru.pingfly.todoping

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardsEventDesktop(backgroundColor: Color, textColor: Color, title: String, date: String, time: String, place: String) {
    Card(
        backgroundColor = backgroundColor,
        modifier = Modifier
            .width(400.dp)
            .height(200.dp)
    ) {
        Column {
            Text(
                text = title,
                fontSize = 36.sp,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            Text(
                text = "Дата: $date\nВремя: $time\nМесто: $place",
                color = textColor,
                modifier = Modifier.padding(end = 5.dp, bottom = 5.dp, start = 5.dp).fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight(350)
            )
        }
    }
}


@Composable
fun CardsTaskDesktop(backgroundColor: Color, textColor: Color, title: String, status: String?, deadline: String, priority: String) {
    Card(
        backgroundColor = backgroundColor,
        modifier = Modifier
            .width(400.dp)
            .height(200.dp)
    ) {
        Column {
            Text(
                text = title,
                fontSize = 36.sp,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            if (status!=null) {
                Text(

                    text = "Статус: $status\nДедлайн: $deadline\nПриоритет: $priority",
                    color = textColor,
                    modifier = Modifier.padding(end = 5.dp, bottom = 5.dp, start = 5.dp)
                        .fillMaxWidth(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight(350)
                )
            }
            else{
                Text(

                    text = "Дедлайн: $deadline\nПриоритет: $priority",
                    color = textColor,
                    modifier = Modifier.padding(end = 5.dp, bottom = 5.dp, start = 5.dp)
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(350)
                )
            }
        }
    }
}


@Composable
fun CardsTaskPhone(backgroundColor: Color, textColor: Color, title: String, status: String?, deadline: String, priority: String) {
    Card(
        backgroundColor = backgroundColor,
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
    ) {
        Column {
            Text(
                text = title,
                fontSize = 24.sp,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            if (status!=null) {
                Text(

                    text = "Статус: $status\nДедлайн: $deadline\nПриоритет: $priority",
                    color = textColor,
                    modifier = Modifier.padding(end = 5.dp, bottom = 5.dp, start = 5.dp)
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(350)
                )
            }
            else{
                Text(

                    text = "Дедлайн: $deadline\nПриоритет: $priority",
                    color = textColor,
                    modifier = Modifier.padding(end = 5.dp, bottom = 5.dp, start = 5.dp)
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(350)
                )
            }
        }
    }
}

@Composable
fun CardsEventPhone(backgroundColor: Color, textColor: Color, title: String, date: String, time: String, place: String) {
    Card(
        backgroundColor = backgroundColor,
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
    ) {
        Column {
            Text(
                text = title,
                fontSize = 24.sp,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            Text(
                text = "Дата: $date\nВремя: $time\nМесто: $place",
                color = textColor,
                modifier = Modifier.padding(end = 5.dp, bottom = 5.dp, start = 5.dp).fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight(350)
            )
        }
    }
}
