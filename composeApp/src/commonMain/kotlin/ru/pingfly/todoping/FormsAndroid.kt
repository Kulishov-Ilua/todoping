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
fun FormsEventAndroid(primaryColor: Color, secondColor: Color, themeColor: Color, onClose:()->Unit, onCreate:()->Unit){
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

    var errorFlag_ds = remember{mutableStateOf(false)}
    var errorFlag_ms = remember{mutableStateOf(false)}
    var errorFlag_ys = remember{mutableStateOf(false)}
    var errorFlag_hs = remember{mutableStateOf(false)}
    var errorFlag_mins = remember{mutableStateOf(false)}

    var errorFlag_de = remember{mutableStateOf(false)}
    var errorFlag_me = remember{mutableStateOf(false)}
    var errorFlag_ye = remember{mutableStateOf(false)}
    var errorFlag_he = remember{mutableStateOf(false)}
    var errorFlag_mine = remember{mutableStateOf(false)}

    Box(
        Modifier.width(600.dp),
        contentAlignment = Alignment.TopCenter) {
        Column(Modifier.padding(top = 50.dp)) {
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
                                isError = errorFlag_ds.value,
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
                                isError = errorFlag_ms.value,
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
                                isError = errorFlag_ys.value,
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
                                isError = errorFlag_hs.value,
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
                                isError = errorFlag_mins.value,
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
                                isError = errorFlag_de.value,
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
                                isError = errorFlag_me.value,
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
                                isError = errorFlag_ye.value,
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
                                isError = errorFlag_he.value,
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
                                isError = errorFlag_mine.value,
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
                                if (day_start.value !="" && day_end.value!="" && month_start.value!="" && month_end.value!="" && year_start.value!="" && year_end.value!="" &&
                                    hour_start.value!="" && hour_end.value!="" && minuts_start.value!="" && minuts_end.value!="") {
                                    var iday_s = day_start.value.toInt()
                                    var iday_e = day_end.value.toInt()
                                    var imonth_s = month_start.value.toInt()
                                    var imonth_e = month_end.value.toInt()
                                    var iyear_s = year_start.value.toInt()
                                    var iyear_e = year_end.value.toInt()
                                    var ihour_s = hour_start.value.toInt()
                                    var ihour_e = hour_end.value.toInt()
                                    var imin_s = minuts_start.value.toInt()
                                    var imin_e = minuts_end.value.toInt()
                                    if (iday_s < 1 || iday_s > 31) {
                                        errorFlag_ds.value = true
                                    }
                                    if (imonth_s < 1 || imonth_s > 12) {
                                        errorFlag_ms.value = true
                                    }
                                    if ((imonth_s == 4 || imonth_s == 6
                                                || imonth_s == 9 || imonth_s == 11) && iday_s > 30
                                    ) {
                                        errorFlag_ds.value = true
                                    }
                                    if ((imonth_s == 2 && iyear_s % 4 == 0 && iday_s > 29) ||
                                        (imonth_s == 2 && iyear_s % 4 != 0 && iday_s > 28)
                                    ) {
                                        errorFlag_ds.value = true
                                    }


                                    if (iday_e < 1 || iday_e > 31) {
                                        errorFlag_de.value = true
                                    }
                                    if (imonth_e < 1 || imonth_e > 12) {
                                        errorFlag_me.value = true
                                    }
                                    if ((imonth_e == 4 || imonth_e == 6
                                                || imonth_e == 9 || imonth_e == 11) && iday_e > 30
                                    ) {
                                        errorFlag_de.value = true
                                    }
                                    if ((imonth_e == 2 && iyear_e % 4 == 0 && iday_e > 29) ||
                                        (imonth_e == 2 && iyear_e % 4 != 0 && iday_e > 28)
                                    ) {
                                        errorFlag_de.value = true
                                    }


                                    if (ihour_s < 0 || ihour_s > 23)
                                    {
                                        errorFlag_hs.value = true
                                    }
                                    if (ihour_e < 0 || ihour_e > 23)
                                    {
                                        errorFlag_he.value = true
                                    }
                                    if (imin_s < 0 || imin_s > 59)
                                    {
                                        errorFlag_mins.value = true
                                    }
                                    if (imin_e < 0 || imin_e > 59)
                                    {
                                        errorFlag_mine.value = true
                                    }
                                }
                                else{
                                    if (day_end.value=="") { errorFlag_de.value = true }
                                    if (month_end.value=="") {errorFlag_me.value = true }
                                    if (year_end.value=="") { errorFlag_ye.value = true }
                                    if (day_start.value ==""){  errorFlag_ds.value = true }
                                    if (month_start.value=="") { errorFlag_ms.value = true}
                                    if (year_start.value=="") { errorFlag_ys.value = true}
                                    if (hour_start.value=="") { errorFlag_hs.value = true}
                                    if (hour_end.value=="") { errorFlag_he.value = true}
                                    if (minuts_start.value=="") { errorFlag_mins.value = true}
                                    if (minuts_end.value=="") { errorFlag_mine.value = true}
                                }
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
fun FormsTaskAndroid(primaryColor:Color, secondColor:Color, themeColor:Color, onClose:()->Unit, onCreate:()->Unit){
    var name = remember { mutableStateOf("") }
    var descr =  remember { mutableStateOf("") }
    var day =  remember { mutableStateOf("") }
    var month =  remember { mutableStateOf("") }
    var year =  remember { mutableStateOf("") }
    var hour =  remember { mutableStateOf("") }
    var minuts =  remember { mutableStateOf("") }
    var parent =  remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(1) }

    var errorFlag_d = remember{mutableStateOf(false)}
    var errorFlag_m = remember{mutableStateOf(false)}
    var errorFlag_y = remember{mutableStateOf(false)}
    var errorFlag_h = remember{mutableStateOf(false)}
    var errorFlag_min = remember{mutableStateOf(false)}

    Box(
        Modifier.width(600.dp),
        contentAlignment = Alignment.TopCenter) {
        Column(Modifier.padding(top = 50.dp)) {
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
                                isError = errorFlag_d.value,
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
                                isError = errorFlag_m.value,
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
                                isError = errorFlag_y.value,
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
                                isError = errorFlag_h.value,
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
                                isError = errorFlag_min.value,
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
                                if (day.value !="" && month.value!=""  && year.value!="" && hour.value!="" && minuts.value!="") {
                                    var iday = day.value.toInt()
                                    var imonth = month.value.toInt()
                                    var iyear = year.value.toInt()

                                    var ihour = hour.value.toInt()
                                    var imin = minuts.value.toInt()

                                    if (iday < 1 || iday > 31) {
                                        errorFlag_d.value = true
                                    }
                                    if (imonth < 1 || imonth > 12) {
                                        errorFlag_m.value = true
                                    }
                                    if ((imonth == 4 || imonth == 6
                                                || imonth == 9 || imonth == 11) && iday > 30
                                    ) {
                                        errorFlag_d.value = true
                                    }
                                    if ((imonth == 2 && iyear % 4 == 0 && iday > 29) ||
                                        (imonth == 2 && iyear % 4 != 0 && iday > 28)
                                    ) {
                                        errorFlag_d.value = true
                                    }

                                    if (ihour < 0 || ihour > 23)
                                    {
                                        errorFlag_h.value = true
                                    }
                                    if (imin < 0 || imin > 59)
                                    {
                                        errorFlag_min.value = true
                                    }

                                }
                                else{
                                    if (day.value=="") { errorFlag_d.value = true }
                                    if (month.value=="") {errorFlag_m.value = true }
                                    if (year.value=="") { errorFlag_y.value = true }

                                    if (hour.value=="") { errorFlag_h.value = true}
                                    if (minuts.value=="") { errorFlag_min.value = true}
                                }
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

/********************************************************************************
 * Отсюда
 */
/**********************************************************************************/
@Composable
fun FormsEventAndroidWithReady(backgroungColor: Color, primaryColor: Color, secondColor: Color, themeColor: Color, name1: String,
                               descr1: String, day_start1: String, month_start1: String, year_start1: String, hour_start1: String, minuts_start1: String,
                               day_end1: String, month_end1:String, year_end1:String, hour_end1:String, minuts_end1:String,
                               place1: String, onClose:()->Unit, onCreate:()->Unit){
    var name = remember { mutableStateOf(name1) }
    var descr =  remember { mutableStateOf(descr1) }
    var day_start =  remember { mutableStateOf(day_start1) }
    var month_start =  remember { mutableStateOf(month_start1) }
    var year_start =  remember { mutableStateOf(year_start1) }
    var hour_start =  remember { mutableStateOf(hour_start1) }
    var minuts_start =  remember { mutableStateOf(minuts_start1) }
    var day_end =  remember { mutableStateOf(day_end1) }
    var month_end =  remember { mutableStateOf(month_end1) }
    var year_end =  remember { mutableStateOf(year_end1) }
    var hour_end =  remember { mutableStateOf(hour_end1) }
    var minuts_end =  remember { mutableStateOf(minuts_end1) }
    var place =  remember { mutableStateOf(place1) }

    var isBusy by remember { mutableStateOf(true) }
    var notification by remember { mutableStateOf(1) }

    var errorFlag_ds = remember{mutableStateOf(false)}
    var errorFlag_ms = remember{mutableStateOf(false)}
    var errorFlag_ys = remember{mutableStateOf(false)}
    var errorFlag_hs = remember{mutableStateOf(false)}
    var errorFlag_mins = remember{mutableStateOf(false)}

    var errorFlag_de = remember{mutableStateOf(false)}
    var errorFlag_me = remember{mutableStateOf(false)}
    var errorFlag_ye = remember{mutableStateOf(false)}
    var errorFlag_he = remember{mutableStateOf(false)}
    var errorFlag_mine = remember{mutableStateOf(false)}

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
                                isError = errorFlag_ds.value,
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
                                isError = errorFlag_ms.value,
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
                                isError = errorFlag_ys.value,
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
                                isError = errorFlag_hs.value,
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
                                isError = errorFlag_mins.value,
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
                                isError = errorFlag_de.value,
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
                                isError = errorFlag_me.value,
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
                                isError = errorFlag_ye.value,
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
                                isError = errorFlag_he.value,
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
                                isError = errorFlag_mine.value,
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
                                if (day_start.value !="" && day_end.value!="" && month_start.value!="" && month_end.value!="" && year_start.value!="" && year_end.value!="" &&
                                    hour_start.value!="" && hour_end.value!="" && minuts_start.value!="" && minuts_end.value!="") {
                                    var iday_s = day_start.value.toInt()
                                    var iday_e = day_end.value.toInt()
                                    var imonth_s = month_start.value.toInt()
                                    var imonth_e = month_end.value.toInt()
                                    var iyear_s = year_start.value.toInt()
                                    var iyear_e = year_end.value.toInt()
                                    var ihour_s = hour_start.value.toInt()
                                    var ihour_e = hour_end.value.toInt()
                                    var imin_s = minuts_start.value.toInt()
                                    var imin_e = minuts_end.value.toInt()
                                    if (iday_s < 1 || iday_s > 31) {
                                        errorFlag_ds.value = true
                                    }
                                    if (imonth_s < 1 || imonth_s > 12) {
                                        errorFlag_ms.value = true
                                    }
                                    if ((imonth_s == 4 || imonth_s == 6
                                                || imonth_s == 9 || imonth_s == 11) && iday_s > 30
                                    ) {
                                        errorFlag_ds.value = true
                                    }
                                    if ((imonth_s == 2 && iyear_s % 4 == 0 && iday_s > 29) ||
                                        (imonth_s == 2 && iyear_s % 4 != 0 && iday_s > 28)
                                    ) {
                                        errorFlag_ds.value = true
                                    }


                                    if (iday_e < 1 || iday_e > 31) {
                                        errorFlag_de.value = true
                                    }
                                    if (imonth_e < 1 || imonth_e > 12) {
                                        errorFlag_me.value = true
                                    }
                                    if ((imonth_e == 4 || imonth_e == 6
                                                || imonth_e == 9 || imonth_e == 11) && iday_e > 30
                                    ) {
                                        errorFlag_de.value = true
                                    }
                                    if ((imonth_e == 2 && iyear_e % 4 == 0 && iday_e > 29) ||
                                        (imonth_e == 2 && iyear_e % 4 != 0 && iday_e > 28)
                                    ) {
                                        errorFlag_de.value = true
                                    }


                                    if (ihour_s < 0 || ihour_s > 23)
                                    {
                                        errorFlag_hs.value = true
                                    }
                                    if (ihour_e < 0 || ihour_e > 23)
                                    {
                                        errorFlag_he.value = true
                                    }
                                    if (imin_s < 0 || imin_s > 59)
                                    {
                                        errorFlag_mins.value = true
                                    }
                                    if (imin_e < 0 || imin_e > 59)
                                    {
                                        errorFlag_mine.value = true
                                    }
                                }
                                else{
                                    if (day_end.value=="") { errorFlag_de.value = true }
                                    if (month_end.value=="") {errorFlag_me.value = true }
                                    if (year_end.value=="") { errorFlag_ye.value = true }
                                    if (day_start.value ==""){  errorFlag_ds.value = true }
                                    if (month_start.value=="") { errorFlag_ms.value = true}
                                    if (year_start.value=="") { errorFlag_ys.value = true}
                                    if (hour_start.value=="") { errorFlag_hs.value = true}
                                    if (hour_end.value=="") { errorFlag_he.value = true}
                                    if (minuts_start.value=="") { errorFlag_mins.value = true}
                                    if (minuts_end.value=="") { errorFlag_mine.value = true}
                                }
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
fun FormsTaskAndroidWithReady(backgroungColor: Color, primaryColor: Color, secondColor: Color, themeColor: Color, name1: String,
                              descr1: String, day1: String, month1: String, year1: String, hour1: String, minuts1: String,
                              parent1: String, onClose:()->Unit, onCreate:()->Unit){
    var name = remember { mutableStateOf(name1) }
    var descr =  remember { mutableStateOf(descr1) }
    var day =  remember { mutableStateOf(day1) }
    var month =  remember { mutableStateOf(month1) }
    var year =  remember { mutableStateOf(year1) }
    var hour =  remember { mutableStateOf(hour1) }
    var minuts =  remember { mutableStateOf(minuts1) }
    var parent =  remember { mutableStateOf(parent1) }

    var priority by remember { mutableStateOf(1) }

    var errorFlag_d = remember{mutableStateOf(false)}
    var errorFlag_m = remember{mutableStateOf(false)}
    var errorFlag_y = remember{mutableStateOf(false)}
    var errorFlag_h = remember{mutableStateOf(false)}
    var errorFlag_min = remember{mutableStateOf(false)}

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
                                isError = errorFlag_d.value,
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
                                isError = errorFlag_m.value,
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
                                isError = errorFlag_y.value,
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
                                isError = errorFlag_h.value,
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
                                isError = errorFlag_min.value,
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
                                if (day.value !="" && month.value!=""  && year.value!="" && hour.value!="" && minuts.value!="") {
                                    var iday = day.value.toInt()
                                    var imonth = month.value.toInt()
                                    var iyear = year.value.toInt()

                                    var ihour = hour.value.toInt()
                                    var imin = minuts.value.toInt()

                                    if (iday < 1 || iday > 31) {
                                        errorFlag_d.value = true
                                    }
                                    if (imonth < 1 || imonth > 12) {
                                        errorFlag_m.value = true
                                    }
                                    if ((imonth == 4 || imonth == 6
                                                || imonth == 9 || imonth == 11) && iday > 30
                                    ) {
                                        errorFlag_d.value = true
                                    }
                                    if ((imonth == 2 && iyear % 4 == 0 && iday > 29) ||
                                        (imonth == 2 && iyear % 4 != 0 && iday > 28)
                                    ) {
                                        errorFlag_d.value = true
                                    }

                                    if (ihour < 0 || ihour > 23)
                                    {
                                        errorFlag_h.value = true
                                    }
                                    if (imin < 0 || imin > 59)
                                    {
                                        errorFlag_min.value = true
                                    }

                                }
                                else{
                                    if (day.value=="") { errorFlag_d.value = true }
                                    if (month.value=="") {errorFlag_m.value = true }
                                    if (year.value=="") { errorFlag_y.value = true }

                                    if (hour.value=="") { errorFlag_h.value = true}
                                    if (minuts.value=="") { errorFlag_min.value = true}
                                }
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
