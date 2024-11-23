//##################################################################################################
//##################################################################################################
//#####################                    Spaces screen                     #######################
//##################################################################################################
//####  Author:KulishovIV                            ###############################################
//####  Version:0.0.1                                ###############################################
//####  Date:23.11.2024                              ###############################################
//##################################################################################################
//##################################################################################################


package ru.pingfly.todoping

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import todoping.composeapp.generated.resources.Res
import todoping.composeapp.generated.resources.ok
import todoping.composeapp.generated.resources.vector


var spaceScreenState by mutableStateOf(0)
//=====================================================================================
//SpaceScreenDesctop
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun SpaceScreenDesctop(backgroungColor: Color, primaryColor:Color, secondColor:Color,themeColor:Color){
    when(spaceScreenState){
        0-> allSpacesScreenDesctop(backgroungColor,primaryColor,secondColor,themeColor)
        -1-> createSpaceDesctop(backgroungColor,Color(32,32,32),primaryColor,secondColor,themeColor,
            onCreate = {

            },
            onClose = {
                spaceScreenState=0
            })
    }
}

//=====================================================================================
//SpaceScreenPhone
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun SpaceScreenPhone(backgroungColor: Color, primaryColor:Color, secondColor:Color,themeColor:Color){
    var createSpace by remember { mutableStateOf(false) }
    var newSpace by remember { mutableStateOf(SpaceCreate("","","",false)) }

    if(createSpace){
        val scope = rememberCoroutineScope()
        scope.launch {

            val server = Reuests()
            server.postSpaces(token,newSpace)
            createSpace=false
            spaceScreenState=0
        }
    }
    //анимация острова
    val animateIsland by animateDpAsState(targetValue =
    if(spaceScreenState==0) 100.dp
    else 500.dp,
        animationSpec = tween(durationMillis = 400)
    )
    //анимация открытия палитры(поворота стрелки)
    val animateRotateColorVector by animateFloatAsState(targetValue =
    if(spaceScreenState==1) 180f else 0f,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    Box() {

        Box(Modifier.fillMaxSize().background(backgroungColor)) {
            when (spaceScreenState) {
                0 -> allSpacesScreenPhone(backgroungColor, primaryColor, secondColor, themeColor)
                1 -> allSpacesScreenPhone(backgroungColor, primaryColor, secondColor, themeColor)
            }
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Box(
                    Modifier.fillMaxWidth().height(animateIsland).background(
                        Color(32, 32, 32),
                        RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                    ), contentAlignment = Alignment.TopCenter
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(Res.drawable.vector),
                            tint = primaryColor,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    if (spaceScreenState == 0) spaceScreenState = 1
                                    else if (spaceScreenState == 1) spaceScreenState = 0
                                }
                                .padding(top=15.dp, bottom = 15.dp)
                                .scale(1f)
                                .rotate(animateRotateColorVector)
                        )
                        if (spaceScreenState == 0) {
                            Text(
                                "Все комнаты", style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                            )
                        }
                        if (spaceScreenState == 1) {
                            LazyColumn {
                                item {
                                    createSpaceAndroid(backgroungColor,
                                        Color(32, 32, 32),
                                        primaryColor,
                                        secondColor,
                                        themeColor,
                                        onCreate = {
                                           cr->
                                            newSpace=cr
                                            createSpace=true
                                        },
                                        onClose = {
                                            spaceScreenState=0
                                        })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//Space
//Variables:
//              id:Int - id class
//              name:String - name
//              description:String - description
//              owner:Int - owner id
//              color:String - color
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
data class Space(val id:Int,val name:String,val description:String, val owner:Int,val color:String)

//=====================================================================================
//allSpacesScreenDesctop
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun allSpacesScreenDesctop(backgroungColor: Color, primaryColor:Color, secondColor:Color,themeColor:Color){
    var listSpaces by remember { mutableStateOf(emptyList<Space>()) }
    //получить лист
    Box(Modifier.fillMaxSize()
        .background(backgroungColor), contentAlignment = Alignment.TopCenter){
        Column {
            Text("Мои пространства", style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            ))
            LazyVerticalGrid(columns = GridCells.Adaptive(300.dp),
                contentPadding = PaddingValues(25.dp) ){
                item{
                    Box(Modifier.width(300.dp)
                        .height(300.dp)
                        .background(themeColor, RoundedCornerShape(20))
                        .clickable {
                            spaceScreenState=-1
                        },
                        contentAlignment = Alignment.Center){
                        Text("Добавить", style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryColor
                        )
                        )
                    }
                }
                items(listSpaces){space->
                    Box(Modifier.width(300.dp)
                        .height(300.dp)
                        .background(parseColor(space.color), RoundedCornerShape(20))
                        .clickable {

                        },
                        contentAlignment = Alignment.Center){
                        Text(space.name, style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryColor
                        )
                        )
                    }
                }
            }
        }
    }

}

//=====================================================================================
//allSpacesScreenDesctop
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun allSpacesScreenPhone(backgroungColor: Color, primaryColor:Color, secondColor:Color,themeColor:Color){
    val server =Reuests()
    var listSpaces by remember { mutableStateOf(emptyList<Space>()) }
    val scope = rememberCoroutineScope()
    scope.launch {
        while(token==""){
            delay(100)
        }
        server.getMySpaces(
            token,
            onSuccess = {res->
                listSpaces= emptyList()
                for(x in res){
                    listSpaces+=Space(x.id,x.name,"",0,x.color)
                }

        }, onFailure = {error -> println(error) })
    }
    //получить лист
    Box(Modifier.fillMaxSize()
        .background(backgroungColor), contentAlignment = Alignment.TopCenter){
        Column {
            Text("Мои пространства", style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            ))
            LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(15.dp) ){
                item{
                    Box(Modifier.width(150.dp)
                        .height(150.dp)
                        .background(themeColor, RoundedCornerShape(20))
                        .clickable {
                            spaceScreenState=1
                        },
                        contentAlignment = Alignment.Center){
                        Text("Добавить", style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryColor
                        )
                        )
                    }
                }
                items(listSpaces){space->
                    Box(Modifier.width(150.dp)
                        .height(150.dp)
                        .background(parseColor(space.color), RoundedCornerShape(20))
                        .clickable {

                        },
                        contentAlignment = Alignment.Center){
                        Text(space.name, style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryColor
                        )
                        )
                    }
                }
            }
        }
    }

}

//=====================================================================================
//Функция парсинга строки в цвет
//=====================================================================================
fun parseColor(colorString: String): Color {
    val rgb = colorString.split(",").map { it.trim().toInt() }
    return Color(rgb[0], rgb[1], rgb[2])
}

//------------------------------------------------------------------------------
//Класс для хранения цветов
//  Поля:
//      uid     - Идентификатор
//      name    - Название
//      color   - Цвет
//      colorS  - Цвет в строке
//------------------------------------------------------------------------------
data class ColorClass(
    val uid:Short,
    val name:String,
    val color:Color,
    val colorS:String
)

val colorList = listOf(
    ColorClass(0,"Красный", Color(192,61,61), "192,61,61"),
    ColorClass(1,"Жёлтый", Color(189,192,61), "189,192,61"),
    ColorClass(2,"Лаймовый", Color(150,192,61), "150,192,61"),
    ColorClass(3,"Зелёный", Color(61,192,82), "61,192,82"),
    ColorClass(4,"Голубой", Color(61,152,192), "61,152,192"),
    ColorClass(5,"Синий", Color(63,61,192), "63,61,192"),
    ColorClass(6,"Розовый", Color(192,61,139), "192,61,139"),
    ColorClass(7,"Пурпурный", Color(169,82,170), "169,82,170"),
)


//=====================================================================================
//createSpaceDesctop
//Input values:
//              backgroungAPPColor:Color - backgroungAPPColor
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - second color
//              themeColor:Color - theme color
//              onClose:()->Unit - action on close
//              onCreate:()->Unit - action on create
//=====================================================================================
@Composable
fun createSpaceDesctop(backgroungAPPColor:Color, backgroungColor: Color, primaryColor:Color, secondColor:Color, themeColor:Color, onClose:()->Unit, onCreate:()->Unit){
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var colorTek by remember { mutableStateOf("") }
    var colorTekname by remember { mutableStateOf("") }
    var colorState by remember { mutableStateOf(false) }
    var isPersonal by remember { mutableStateOf(true) }
    //анимация полосок цветов
    val animateBorderColor by animateDpAsState(targetValue =
    if(!colorState) 450.dp else 350.dp,
        animationSpec = tween(durationMillis = 400)
    )
    //анимация открытия палитры(поворота стрелки)
    val animateRotateColorVector by animateFloatAsState(targetValue =
    if(!colorState) 180f else 0f,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    Box(Modifier.fillMaxSize()
        .background(backgroungAPPColor), contentAlignment = Alignment.Center) {
        Box(
            Modifier.width(600.dp)
                .background(backgroungColor, RoundedCornerShape(5)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(Modifier.padding(50.dp)) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "Создать пространство", style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor
                        )
                    )
                }
                Box(Modifier.padding(top = 25.dp, bottom = 25.dp)) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
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
                Box(Modifier.padding(bottom = 25.dp)) {
                    TextField(
                        value = description,
                        onValueChange = { description = it },
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
                //анимация изменения бокса палитры
                val animateColorBox by animateDpAsState(
                    targetValue = if (!colorState) 50.dp else 210.dp,
                    animationSpec = tween(durationMillis = 400)
                )
                Box(
                    modifier = Modifier

                        .fillMaxWidth()
                        .height(animateColorBox)
                        .border(
                            width = if (colorState) 3.dp else 0.dp,
                            color = primaryColor,
                            shape = RoundedCornerShape(8)
                        ), contentAlignment = Alignment.TopCenter
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            Modifier
                                .height(50.dp)
                                .clickable {
                                    colorState = !colorState
                                }, contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Row(
                                    modifier = Modifier
                                        .padding(start = 25.dp, end = 25.dp)
                                        .height(48.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if (colorTek == "") "цвет" else colorTekname,
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = primaryColor
                                        )
                                    )

                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .padding(start = 15.dp)
                                                .width(25.dp)
                                                .height(25.dp)
                                                .background(
                                                    if (colorTek != "") parseColor(
                                                        colorTek
                                                    ) else backgroungColor,
                                                    shape = RoundedCornerShape(4)
                                                ), contentAlignment = Alignment.Center
                                        ) {
                                            if (colorTek != "" && !colorState) {
                                                Icon(
                                                    painter = painterResource(Res.drawable.ok),
                                                    tint = primaryColor,
                                                    contentDescription = "",
                                                    modifier = Modifier
                                                        .scale(0.7f)

                                                )
                                            } else {
                                                Icon(
                                                    painter = painterResource(Res.drawable.vector),
                                                    tint = primaryColor,
                                                    contentDescription = "",
                                                    modifier = Modifier
                                                        .scale(0.7f)
                                                        .rotate(animateRotateColorVector)
                                                )
                                            }
                                        }

                                    }


                                }
                                Box(
                                    Modifier
                                        .width(animateBorderColor)
                                        .height(2.dp)
                                        .background(primaryColor)
                                )
                            }
                        }
                        if (colorState) {
                            LazyColumn(Modifier.height(160.dp)) {
                                items(colorList) { color ->
                                    Box(
                                        Modifier
                                            .height(50.dp)
                                            .clickable {
                                                if (color.colorS == colorTek) {
                                                    colorTek = ""
                                                    colorTekname = ""
                                                } else {
                                                    colorTek = color.colorS
                                                    colorTekname = color.name
                                                }
                                                colorState = false
                                            }, contentAlignment = Alignment.Center
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Row(
                                                modifier = Modifier
                                                    .padding(start = 25.dp, end = 25.dp)
                                                    .height(48.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = color.name, style = TextStyle(
                                                        fontSize = 20.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = primaryColor
                                                    )
                                                )
                                                Box(
                                                    modifier = Modifier.fillMaxSize(),
                                                    contentAlignment = Alignment.CenterEnd
                                                ) {
                                                    Box(
                                                        Modifier
                                                            .width(25.dp)
                                                            .height(25.dp)
                                                            .background(
                                                                color.color,
                                                                shape = RoundedCornerShape(4)
                                                            ),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        if (colorTek == color.colorS) {
                                                            Icon(
                                                                painter = painterResource(Res.drawable.ok),
                                                                contentDescription = "",
                                                                tint = primaryColor
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            Box(
                                                Modifier
                                                    .width(animateBorderColor)
                                                    .height(2.dp)
                                                    .background(primaryColor)
                                            )
                                        }

                                    }

                                }
                            }
                        }
                    }
                }



                Box(
                    Modifier.padding(top = 25.dp, bottom = 25.dp).width(200.dp).height(80.dp)
                        .border(3.dp, primaryColor, RoundedCornerShape(8))
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Box(Modifier.fillMaxHeight().weight(1f).background(
                            if (isPersonal) themeColor else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                            .clickable {
                                isPersonal = !isPersonal
                            }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Личная", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = primaryColor
                                )
                            )
                        }

                        Box(Modifier.fillMaxHeight().weight(1f).background(
                            if (!isPersonal) themeColor else Color.Transparent,
                            RoundedCornerShape(4.dp)
                        )
                            .clickable {
                                isPersonal = !isPersonal
                            }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "админ", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = primaryColor
                                )
                            )
                        }
                    }
                }
                Row {
                    Box(Modifier.padding(end = 20.dp).width(230.dp).height(70.dp).background(
                        Color(207, 41, 41),
                        RoundedCornerShape(8)
                    )
                        .clickable {
                            onClose()
                        }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Отмена", style = TextStyle(
                                color = primaryColor,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Box(Modifier.padding(end = 20.dp).width(230.dp).height(70.dp).background(
                        themeColor,
                        RoundedCornerShape(8)
                    )
                        .clickable {
                            onCreate()
                        }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Сохранить", style = TextStyle(
                                color = primaryColor,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun createSpaceAndroid(backgroungAPPColor:Color, backgroungColor: Color, primaryColor:Color, secondColor:Color, themeColor:Color, onClose:()->Unit, onCreate:(SpaceCreate)->Unit){
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var colorTek by remember { mutableStateOf("") }
    var colorTekname by remember { mutableStateOf("") }
    var colorState by remember { mutableStateOf(false) }
    var isPersonal by remember { mutableStateOf(true) }
    //анимация полосок цветов
    val animateBorderColor by animateDpAsState(targetValue =
    if(!colorState) 450.dp else 350.dp,
        animationSpec = tween(durationMillis = 400)
    )

    //анимация открытия палитры(поворота стрелки)
    val animateRotateColorVector by animateFloatAsState(targetValue =
    if(!colorState) 180f else 0f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

        Box(
            Modifier.fillMaxWidth()
                .background(backgroungColor, RoundedCornerShape(5)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(Modifier.padding(15.dp)) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "Создать пространство", style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor
                        )
                    )
                }
                Box(Modifier.padding(top = 25.dp, bottom = 25.dp)) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
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
                Box(Modifier.padding(bottom = 25.dp)) {
                    TextField(
                        value = description,
                        onValueChange = { description = it },
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
                //анимация изменения бокса палитры
                val animateColorBox by animateDpAsState(
                    targetValue = if (!colorState) 50.dp else 210.dp,
                    animationSpec = tween(durationMillis = 400)
                )
                Box(
                    modifier = Modifier

                        .fillMaxWidth()
                        .height(animateColorBox)
                        .border(
                            width = if (colorState) 3.dp else 0.dp,
                            color = primaryColor,
                            shape = RoundedCornerShape(8)
                        ), contentAlignment = Alignment.TopCenter
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            Modifier
                                .height(50.dp)
                                .clickable {
                                    colorState = !colorState
                                }, contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Row(
                                    modifier = Modifier
                                        .padding(start = 25.dp, end = 25.dp)
                                        .height(48.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if (colorTek == "") "цвет" else colorTekname,
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = primaryColor
                                        )
                                    )

                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .padding(start = 15.dp)
                                                .width(25.dp)
                                                .height(25.dp)
                                                .background(
                                                    if (colorTek != "") parseColor(
                                                        colorTek
                                                    ) else backgroungColor,
                                                    shape = RoundedCornerShape(4)
                                                ), contentAlignment = Alignment.Center
                                        ) {
                                            if (colorTek != "" && !colorState) {
                                                Icon(
                                                    painter = painterResource(Res.drawable.ok),
                                                    tint = primaryColor,
                                                    contentDescription = "",
                                                    modifier = Modifier
                                                        .scale(0.7f)

                                                )
                                            } else {
                                                Icon(
                                                    painter = painterResource(Res.drawable.vector),
                                                    tint = primaryColor,
                                                    contentDescription = "",
                                                    modifier = Modifier
                                                        .scale(0.7f)
                                                        .rotate(animateRotateColorVector)
                                                )
                                            }
                                        }

                                    }


                                }
                                Box(
                                    Modifier
                                        .width(animateBorderColor)
                                        .height(2.dp)
                                        .background(primaryColor)
                                )
                            }
                        }
                        if (colorState) {
                            LazyColumn(Modifier.height(160.dp)) {
                                items(colorList) { color ->
                                    Box(
                                        Modifier
                                            .height(50.dp)
                                            .clickable {
                                                if (color.colorS == colorTek) {
                                                    colorTek = ""
                                                    colorTekname = ""
                                                } else {
                                                    colorTek = color.colorS
                                                    colorTekname = color.name
                                                }
                                                colorState = false
                                            }, contentAlignment = Alignment.Center
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Row(
                                                modifier = Modifier
                                                    .padding(start = 25.dp, end = 25.dp)
                                                    .height(48.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = color.name, style = TextStyle(
                                                        fontSize = 20.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = primaryColor
                                                    )
                                                )
                                                Box(
                                                    modifier = Modifier.fillMaxSize(),
                                                    contentAlignment = Alignment.CenterEnd
                                                ) {
                                                    Box(
                                                        Modifier
                                                            .width(25.dp)
                                                            .height(25.dp)
                                                            .background(
                                                                color.color,
                                                                shape = RoundedCornerShape(4)
                                                            ),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        if (colorTek == color.colorS) {
                                                            Icon(
                                                                painter = painterResource(Res.drawable.ok),
                                                                contentDescription = "",
                                                                tint = primaryColor
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            Box(
                                                Modifier
                                                    .width(animateBorderColor)
                                                    .height(2.dp)
                                                    .background(primaryColor)
                                            )
                                        }

                                    }

                                }
                            }
                        }
                    }
                }



                Box(
                    Modifier.padding(top = 25.dp, bottom = 25.dp).width(200.dp).height(80.dp)
                        .border(3.dp, primaryColor, RoundedCornerShape(8))
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Box(Modifier.fillMaxHeight().weight(1f).background(
                            if (isPersonal) themeColor else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                            .clickable {
                                isPersonal = !isPersonal
                            }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Личная", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = primaryColor
                                )
                            )
                        }

                        Box(Modifier.fillMaxHeight().weight(1f).background(
                            if (!isPersonal) themeColor else Color.Transparent,
                            RoundedCornerShape(4.dp)
                        )
                            .clickable {
                                isPersonal = !isPersonal
                            }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "админ", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = primaryColor
                                )
                            )
                        }
                    }
                }
                Row {
                    Box(Modifier.padding(end = 20.dp).width(145.dp).height(70.dp).background(
                        Color(207, 41, 41),
                        RoundedCornerShape(8)
                    )
                        .clickable {
                            onClose()
                        }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Отмена", style = TextStyle(
                                color = primaryColor,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Box(Modifier.padding(end = 20.dp).width(145.dp).height(70.dp).background(
                        themeColor,
                        RoundedCornerShape(8)
                    )
                        .clickable {

                            if(name!=""&&colorTek!=""){
                                val spaceN = SpaceCreate(name,description,colorTek,!isPersonal)
                                onCreate(spaceN)
                            }

                        }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Сохранить", style = TextStyle(
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



