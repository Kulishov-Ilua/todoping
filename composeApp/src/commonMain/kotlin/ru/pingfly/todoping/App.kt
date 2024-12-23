package ru.pingfly.todoping

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import todoping.composeapp.generated.resources.Res
import todoping.composeapp.generated.resources.compose_multiplatform
import todoping.composeapp.generated.resources.exit
import todoping.composeapp.generated.resources.more

var appState by mutableStateOf(0)
var drawerState by mutableStateOf(false)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(topBar = {shapkaDesctop(Color(7,7,7), Color.White,  Color(169,11,238))},
            ){
            when(appState){
                -1->{}
                0->{
                    DesctopProfile( Color(7,7,7), Color.White, Color(122,122,122), Color(169,11,238))
                }
                20->{
                    SpaceScreenDesctop(
                        Color(7,7,7), Color.White, Color(122,122,122), Color(169,11,238))
                }
            }
        }
    }
}
//=====================================================================================
//shapkaDesctop
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun shapkaDesctop(backgroungColor: Color, primaryColor: Color, themeColor: Color){

    Box(Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(Color.Black)){
        Row( Modifier
            .padding(start=50.dp,end=50.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically){


            Text(
                text = "todo",
                fontSize = 36.sp,
                color = themeColor,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = ".Ping()",
                fontSize = 36.sp,
                color = primaryColor,
                fontWeight = FontWeight.SemiBold,
            )

            Row( Modifier
                .width(362.dp)
                .height(30.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Home",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Left,
                    color = if(appState>=0&& appState<10) themeColor else primaryColor,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start =25.dp, end = 25.dp).clickable {
                        appState=0
                    }
                )
                Text(
                    text = "MyDay",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = if(appState>=10&& appState<20) themeColor else primaryColor,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start =25.dp, end = 25.dp).clickable {
                        appState=10
                    }
//modifier = Modifier.fillMaxWidth().fillMaxHeight()
                )
                Text(
                    text = "Spaces",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Right,
                    color = if(appState>=20&& appState<30) themeColor else primaryColor,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start =25.dp, end = 25.dp).clickable {
                        appState=20
                    }
//modifier = Modifier.fillMaxWidth().fillMaxHeight()
                )
            }
         Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
             Text(
                 text = "Exit",
                 fontSize = 24.sp,
//textAlign = TextAlign.Center,
                 color = Color.Red,
                 fontWeight = FontWeight.Bold,
                 modifier = Modifier.clickable {
                     appState=-1
                 }
             )
         }
        }

    }
}
//=====================================================================================
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun phoneScreen(backgroungColor: Color, primaryColor:Color, secondColor:Color,themeColor:Color){
    Scaffold(topBar = {
        shapkaPhone(backgroungColor,primaryColor,themeColor)
    }, backgroundColor = backgroungColor){
        if(drawerState){
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Home", style = TextStyle(
                        fontSize = 24.sp,
                        color = if(appState>=0&& appState<10) themeColor else primaryColor
                    ), modifier = Modifier.padding(7.dp).clickable {
                        appState=0
                        drawerState=false
                    })
                    Text("MyDay", style = TextStyle(
                        fontSize = 24.sp,
                        color = if(appState>=10&& appState<20) themeColor else primaryColor
                    ), modifier = Modifier.padding(7.dp).clickable {
                        appState=10
                        drawerState=false
                    })
                    Text("Spaces", style = TextStyle(
                        fontSize = 24.sp,
                        color = if(appState>=20&& appState<30) themeColor else primaryColor
                    ), modifier = Modifier.padding(7.dp).clickable {
                        appState=20
                        drawerState=false
                    })
                    Text("Spaces Adm", style = TextStyle(
                        fontSize = 24.sp,
                        color = if(adm) themeColor else primaryColor
                    ), modifier = Modifier.padding(7.dp).clickable {
                        adm=!adm
                        spaceScreenState=5
                        drawerState=false
                    })
                }
            }
        }else{
            when(appState){
                0-> CompactProfile(backgroungColor,primaryColor,secondColor,themeColor)
                20-> SpaceScreenPhone(backgroungColor,primaryColor,secondColor,themeColor)
                -20->SpaceScreenPhone(backgroungColor,primaryColor,secondColor,themeColor)
            }
        }
    }
}

@Composable
fun shapkaPhone( backgroungColor: Color,primaryColor:Color,themeColor:Color){
    Box(Modifier
        .fillMaxWidth()
        .height(50.dp)
        , contentAlignment = Alignment.Center){
            Row( Modifier
                .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "todo",
                    fontSize = 24.sp,
                    color = themeColor,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = ".Ping()",
                    fontSize = 24.sp,
                    color = primaryColor,
                    fontWeight = FontWeight.SemiBold,
                )
            }

        }
    Box(Modifier.padding(end=15.dp).fillMaxWidth(),contentAlignment = Alignment.CenterEnd){
        Image(painter = painterResource(if(drawerState==false) Res.drawable.more else Res.drawable.exit), contentDescription="menu_icon_mobile", Modifier
            .clickable {
                drawerState=!drawerState
            })
    }



}

var adm by mutableStateOf(false)