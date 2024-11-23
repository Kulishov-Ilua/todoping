package ru.pingfly.todoping

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormsEventAndroid(backgroungColor: Color, primaryColor: Color, secondColor: Color, themeColor: Color, onClose:()->Unit, onCreate:()->Unit){
    var name = remember { mutableStateOf("") }
    var descr =  remember { mutableStateOf("") }
    var day_start =  remember { mutableStateOf("") }
    var month_start =  remember { mutableStateOf("") }
    var year_start =  remember { mutableStateOf("") }
    var hour_start =  remember { mutableStateOf("") }
    var minuts_start =  remember { mutableStateOf("") }
    var day_end =  remember { mutableStateOf("") }
    var month_end =  remember { mutableStateOf("") }
    var year_end =  remember { mutableStateOf("") }
    var hour_end =  remember { mutableStateOf("") }
    var minuts_end =  remember { mutableStateOf("") }
    var place =  remember { mutableStateOf("") }
    var isBusy by remember { mutableStateOf(true) }
    var notification by remember { mutableStateOf(1) }
    Box(
        Modifier.width(600.dp)
            .background(backgroungColor, RoundedCornerShape(5)),
        contentAlignment = Alignment.TopCenter) {
        Column(Modifier.padding(50.dp)) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    "Создать событие", style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                )
            }
            LazyColumn {
                item {
                    Box(Modifier.padding(top = 25.dp, bottom = 25.dp)) {
                        TextField(
                            value = name.value,
                            onValueChange = { name.value = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Название",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.White
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = primaryColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = secondColor,
                                textColor = primaryColor,
                                disabledTextColor = primaryColor,
                                errorLeadingIconColor = Color(232, 51, 31)
                            )
                        )
                    }
                }
                item {
                    Box(Modifier.padding(bottom = 25.dp)) {
                        TextField(
                            value = descr.value,
                            onValueChange = { descr.value = it },
                            modifier = Modifier.fillMaxWidth().height(250.dp),
                            label = {
                                Text(
                                    text = "Описание",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.White
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = primaryColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = secondColor,
                                textColor = primaryColor,
                                disabledTextColor = primaryColor,
                                errorLeadingIconColor = Color(232, 51, 31)
                            )
                        )

                    }
                }
                item {
                    Box(Modifier.fillMaxWidth()) {
                        Text(
                            "Начало", style = TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        )
                    }
                }
                item {
                    Box(Modifier.padding(bottom = 25.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                            TextField(
                                value = day_start.value,
                                onValueChange = { day_start.value = it },
                                modifier = Modifier.width(88.dp),
                                label = {
                                    Text(
                                        text = "день",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = month_start.value,
                                onValueChange = { month_start.value = it },
                                modifier = Modifier.width(104.dp),
                                label = {
                                    Text(
                                        text = "месяц",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = year_start.value,
                                onValueChange = { year_start.value = it },
                                modifier = Modifier.width(88.dp),
                                label = {
                                    Text(
                                        text = "год",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )
                        }
                    }
                }

                item {
                    Box(Modifier.padding(bottom = 25.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            TextField(
                                value = hour_start.value,
                                onValueChange = { hour_start.value = it },
                                modifier = Modifier.width(145.dp),
                                label = {
                                    Text(
                                        text = "часы",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = minuts_start.value,
                                onValueChange = { minuts_start.value = it },
                                modifier = Modifier.width(145.dp),
                                label = {
                                    Text(
                                        text = "минуты",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )
                        }
                    }
                }

                item {
                    Box(Modifier.fillMaxWidth()) {
                        Text(
                            "Конец", style = TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        )
                    }
                }
                item {
                    Box(Modifier.padding(bottom = 25.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                            TextField(
                                value = day_end.value,
                                onValueChange = { day_end.value = it },
                                modifier = Modifier.width(88.dp),
                                label = {
                                    Text(
                                        text = "день",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = month_end.value,
                                onValueChange = { month_end.value = it },
                                modifier = Modifier.width(104.dp),
                                label = {
                                    Text(
                                        text = "месяц",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = year_end.value,
                                onValueChange = { year_end.value = it },
                                modifier = Modifier.width(88.dp),
                                label = {
                                    Text(
                                        text = "год",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )
                        }
                    }
                }
                item {
                    Box(Modifier.padding(bottom = 25.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            TextField(
                                value = hour_end.value,
                                onValueChange = { hour_end.value = it },
                                modifier = Modifier.width(145.dp),
                                label = {
                                    Text(
                                        text = "часы",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = minuts_end.value,
                                onValueChange = { minuts_end.value = it },
                                modifier = Modifier.width(145.dp),
                                label = {
                                    Text(
                                        text = "минуты",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )
                        }
                    }
                }
                item{
                    Box(Modifier.padding(bottom = 25.dp)) {
                        TextField(
                            value = place.value,
                            onValueChange = { place.value = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Место",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.White
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = primaryColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = secondColor,
                                textColor = primaryColor,
                                disabledTextColor = primaryColor,
                                errorLeadingIconColor = Color(232, 51, 31)
                            )
                        )
                    }
                }
                item{
                    Box(Modifier.padding(top=25.dp, bottom = 25.dp).width(200.dp).height(80.dp).border(3.dp,primaryColor, RoundedCornerShape(8))){
                        Row(Modifier.fillMaxWidth()) {
                            Box(Modifier.fillMaxHeight().weight(1f).background(if(isBusy) themeColor else Color.Transparent,
                                RoundedCornerShape(8.dp)
                            )
                                .clickable {
                                    isBusy=!isBusy
                                }, contentAlignment = Alignment.Center){
                                Text("Занят", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color=primaryColor
                                )
                                )
                            }

                            Box(Modifier.fillMaxHeight().weight(1f).background(if(!isBusy) themeColor else Color.Transparent,
                                RoundedCornerShape(4.dp)
                            )
                                .clickable {
                                    isBusy=!isBusy
                                }, contentAlignment = Alignment.Center){
                                Text("Свободен", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color=primaryColor
                                )
                                )
                            }
                        }
                    }
                    Box(Modifier.fillMaxWidth()) {
                        Text(
                            "Напоминание", style = TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        )
                    }
                    Box(Modifier.padding(top=25.dp, bottom = 25.dp).width(200.dp).height(80.dp).border(3.dp,primaryColor, RoundedCornerShape(8))){
                        Row(Modifier.fillMaxWidth()) {
                            Box(Modifier.fillMaxHeight().weight(1f).background(if(notification==1) themeColor else Color.Transparent,
                                RoundedCornerShape(8.dp)
                            )
                                .clickable {
                                    notification=1
                                }, contentAlignment = Alignment.Center){
                                Text("нет", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color=primaryColor
                                )
                                )
                            }

                            Box(Modifier.fillMaxHeight().weight(1f).background(if(notification==2) themeColor else Color.Transparent,
                                RoundedCornerShape(4.dp)
                            )
                                .clickable {
                                    notification=2
                                }, contentAlignment = Alignment.Center){
                                Text("За 30 минут", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color=primaryColor
                                )
                                )
                            }
                            Box(Modifier.fillMaxHeight().weight(1f).background(if(notification==3) themeColor else Color.Transparent,
                                RoundedCornerShape(8.dp)
                            )
                                .clickable {
                                    notification=3
                                }, contentAlignment = Alignment.Center){
                                Text("За 15 минут", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color=primaryColor
                                )
                                )
                            }
                        }
                    }
                    Row {
                        Box(Modifier.padding(end=20.dp).width(145.dp).height(70.dp).background(
                            Color(207,41,41),
                            RoundedCornerShape(8)
                        )
                            .clickable {
                                onClose()
                            }, contentAlignment = Alignment.Center){
                            Text("Отмена", style = TextStyle(
                                color = primaryColor,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            )
                        }
                        Box(Modifier.padding(end=20.dp).width(145.dp).height(70.dp).background(themeColor,
                            RoundedCornerShape(8)
                        )
                            .clickable {
                                onCreate()
                            }, contentAlignment = Alignment.Center){
                            Text("Сохранить", style = TextStyle(
                                color = primaryColor,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            )
                        }
                    }
                }
            }

        }
    }

}



@Composable
fun FormsTaskAndroid(backgroungColor: Color, primaryColor:Color, secondColor:Color, themeColor:Color, onClose:()->Unit, onCreate:()->Unit){
    var name = remember { mutableStateOf("") }
    var descr =  remember { mutableStateOf("") }
    var day =  remember { mutableStateOf("") }
    var month =  remember { mutableStateOf("") }
    var year =  remember { mutableStateOf("") }
    var hour =  remember { mutableStateOf("") }
    var minuts =  remember { mutableStateOf("") }
    var parent =  remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(1) }
    Box(
        Modifier.width(600.dp)
            .background(backgroungColor, RoundedCornerShape(5)),
        contentAlignment = Alignment.TopCenter) {
        Column(Modifier.padding(50.dp)) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    "Создать задачу", style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                )
            }
            LazyColumn {
                item {
                    Box(Modifier.padding(top = 25.dp, bottom = 25.dp)) {
                        TextField(
                            value = name.value,
                            onValueChange = { name.value = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Название",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.White
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = primaryColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = secondColor,
                                textColor = primaryColor,
                                disabledTextColor = primaryColor,
                                errorLeadingIconColor = Color(232, 51, 31)
                            )
                        )
                    }
                }
                item {
                    Box(Modifier.padding(bottom = 25.dp)) {
                        TextField(
                            value = descr.value,
                            onValueChange = { descr.value = it },
                            modifier = Modifier.fillMaxWidth().height(250.dp),
                            label = {
                                Text(
                                    text = "Описание",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.White
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = primaryColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = secondColor,
                                textColor = primaryColor,
                                disabledTextColor = primaryColor,
                                errorLeadingIconColor = Color(232, 51, 31)
                            )
                        )

                    }
                }
                item {
                    Box(Modifier.fillMaxWidth()) {
                        Text(
                            "Дедлайн", style = TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        )
                    }
                }
                item {
                    Box(Modifier.padding(bottom = 25.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                            TextField(
                                value = day.value,
                                onValueChange = { day.value = it },
                                modifier = Modifier.width(88.dp),
                                label = {
                                    Text(
                                        text = "день",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = month.value,
                                onValueChange = { month.value = it },
                                modifier = Modifier.width(104.dp),
                                label = {
                                    Text(
                                        text = "месяц",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = year.value,
                                onValueChange = { year.value = it },
                                modifier = Modifier.width(88.dp),
                                label = {
                                    Text(
                                        text = "год",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )
                        }
                    }
                }

                item {
                    Box(Modifier.padding(bottom = 25.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            TextField(
                                value = hour.value,
                                onValueChange = { hour.value = it },
                                modifier = Modifier.width(145.dp),
                                label = {
                                    Text(
                                        text = "часы",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )

                            TextField(
                                value = minuts.value,
                                onValueChange = { minuts.value = it },
                                modifier = Modifier.width(145.dp),
                                label = {
                                    Text(
                                        text = "минуты",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = primaryColor,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = secondColor,
                                    textColor = primaryColor,
                                    disabledTextColor = primaryColor,
                                    errorLeadingIconColor = Color(232, 51, 31)
                                )
                            )
                        }
                    }
                }

                item{
                    Box(Modifier.padding(bottom = 25.dp)) {
                        TextField(
                            value = parent.value,
                            onValueChange = { parent.value = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Родитель",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.White
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = primaryColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = secondColor,
                                textColor = primaryColor,
                                disabledTextColor = primaryColor,
                                errorLeadingIconColor = Color(232, 51, 31)
                            )
                        )
                    }
                }
                item{

                    Box(Modifier.fillMaxWidth()) {
                        Text(
                            "Приоритет", style = TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        )
                    }
                    Box(Modifier.padding(top=25.dp, bottom = 25.dp).width(200.dp).height(80.dp).border(3.dp,primaryColor, RoundedCornerShape(8))){
                        Row(Modifier.fillMaxWidth()) {
                            Box(Modifier.fillMaxHeight().weight(1f).background(if(priority==1) themeColor else Color.Transparent,
                                RoundedCornerShape(8.dp))
                                .clickable {
                                    priority=1
                                }, contentAlignment = Alignment.Center){
                                Text("не важно", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color=primaryColor
                                )
                                )
                            }

                            Box(Modifier.fillMaxHeight().weight(1f).background(if(priority==2) themeColor else Color.Transparent,
                                RoundedCornerShape(4.dp))
                                .clickable {
                                    priority=2
                                }, contentAlignment = Alignment.Center){
                                Text("важно", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color=primaryColor
                                )
                                )
                            }
                            Box(Modifier.fillMaxHeight().weight(1f).background(if(priority==3) themeColor else Color.Transparent,
                                RoundedCornerShape(8.dp))
                                .clickable {
                                    priority=3
                                }, contentAlignment = Alignment.Center){
                                Text("очень важно", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color=primaryColor
                                )
                                )
                            }
                        }
                    }
                    Row {
                        Box(Modifier.padding(end=20.dp).width(145.dp).height(70.dp).background(Color(207,41,41),
                            RoundedCornerShape(8)
                        )
                            .clickable {
                                onClose()
                            }, contentAlignment = Alignment.Center){
                            Text("Отмена", style = TextStyle(
                                color = primaryColor,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            )
                        }
                        Box(Modifier.padding(end=20.dp).width(145.dp).height(70.dp).background(themeColor,
                            RoundedCornerShape(8)
                        )
                            .clickable {
                                onCreate()
                            }, contentAlignment = Alignment.Center){
                            Text("Сохранить", style = TextStyle(
                                color = primaryColor,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            )
                        }
                    }
                }
            }

        }
    }

}
