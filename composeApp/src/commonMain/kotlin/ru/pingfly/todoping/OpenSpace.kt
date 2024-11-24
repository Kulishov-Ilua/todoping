//##################################################################################################
//##################################################################################################
//#####################                  Open space screens                  #######################
//##################################################################################################
//####  Author:KulishovIV                            ###############################################
//####  Version:0.0.1                                ###############################################
//####  Date:23.11.2024                              ###############################################
//##################################################################################################
//##################################################################################################

package ru.pingfly.todoping

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

var spaceTek by   mutableStateOf(SpaceDetail(0,"",null,"", emptyList(), emptyList(), emptyList(),false))

//=====================================================================================
//spaceScreenAdmin
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun spaceScreenAdmin(idSpace:Int, islandDp: Dp, backgroungColor: Color, primaryColor: Color, secondColor: Color, themeColor: Color){
    var stateAdminView by remember { mutableStateOf(true) }
    val server = Reuests()
    val scope = rememberCoroutineScope()
    scope.launch {
        server.getSpace(idSpace, token, onSuccess = {
            res-> spaceTek=res
        }, onFailure = {res-> println(res) })
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.padding(start=15.dp,end=15.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            Text(spaceTek.name, style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            ))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                Box(
                    Modifier.padding(top = 25.dp, bottom = 25.dp).width(200.dp).height(50.dp)
                        .border(3.dp, primaryColor, RoundedCornerShape(8))
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Box(Modifier.fillMaxHeight().weight(1f).background(
                            if (stateAdminView) themeColor else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                            .clickable {
                                stateAdminView = !stateAdminView
                            }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Дом", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = primaryColor
                                )
                            )
                        }

                        Box(Modifier.fillMaxHeight().weight(1f).background(
                            if (!stateAdminView) themeColor else Color.Transparent,
                            RoundedCornerShape(4.dp)
                        )
                            .clickable {
                                stateAdminView = !stateAdminView
                            }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Стат", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = primaryColor
                                )
                            )
                        }
                    }
                }
            }
        }
       if(stateAdminView){
           LazyColumn(Modifier.padding(bottom =islandDp ),horizontalAlignment = Alignment.CenterHorizontally) {
               item{
                   Box(Modifier.padding(top=7.dp, bottom = 7.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                       Text("События", style = TextStyle(
                           fontSize = 24.sp,
                           fontWeight = FontWeight.Bold,
                           color = primaryColor
                       )
                       )
                   }
               }
               item {
                   Box(Modifier.padding(top=7.dp, bottom = 7.dp).width(300.dp).height(150.dp).background(themeColor,
                       RoundedCornerShape(10)
                   ).clickable {
                       spaceScreenState=4
                   }, contentAlignment = Alignment.Center){
                       Text("Создать", style = TextStyle(
                           fontSize = 24.sp,
                           color= primaryColor
                       )
                       )
                   }
               }
               items(spaceTek.events){
                   event->
                   Box(Modifier.padding(top=7.dp, bottom = 7.dp)) {
                       CardsEventPhone(
                           secondColor,
                           primaryColor,
                           event.name,
                           event.date,
                           event.time,
                           event.place
                       )
                   }
               }
               item{
                   Box(Modifier.padding(top=7.dp, bottom = 7.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                       Text("Задачи", style = TextStyle(
                           fontSize = 24.sp,
                           fontWeight = FontWeight.Bold,
                           color = primaryColor
                       )
                       )
                   }
               }
               item {
                   Box(Modifier.padding(top=7.dp, bottom = 7.dp).width(300.dp).height(150.dp).background(themeColor,
                       RoundedCornerShape(10)
                   ).clickable {
                       spaceScreenState=3
                   }, contentAlignment = Alignment.Center){
                       Text("Создать", style = TextStyle(
                           fontSize = 24.sp,
                           color= primaryColor
                       )
                       )
                   }
               }
               items(spaceTek.tasks){
                       task->
                   Box(Modifier.padding(top=7.dp, bottom = 7.dp)){
                   CardsTaskPhone(secondColor,primaryColor,task.name,task.status,task.deadline,task.priority)

                   }
           }}


       }else{
           adminStata(backgroungColor,primaryColor,secondColor,themeColor)
       }
    }
}


//=====================================================================================
//spaceScreen
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun spaceScreenUser(idSpace:Int, islandDp: Dp, backgroungColor: Color, primaryColor: Color, secondColor: Color, themeColor: Color){
    var stateAdminView by remember { mutableStateOf(true) }
    val server = Reuests()
    val scope = rememberCoroutineScope()
    scope.launch {
        server.getSpace(idSpace, token, onSuccess = {
                res-> spaceTek=res
        }, onFailure = {res-> println(res) })
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.padding(start=15.dp,end=15.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            Text(spaceTek.name, style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            ))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                Box(
                    Modifier.padding(top = 25.dp, bottom = 25.dp).width(200.dp).height(50.dp)
                        .border(3.dp, primaryColor, RoundedCornerShape(8))
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Box(Modifier.fillMaxHeight().weight(1f).background(
                            if (stateAdminView) themeColor else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                            .clickable {
                                stateAdminView = !stateAdminView
                            }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Дом", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = primaryColor
                                )
                            )
                        }

                        Box(Modifier.fillMaxHeight().weight(1f).background(
                            if (!stateAdminView) themeColor else Color.Transparent,
                            RoundedCornerShape(4.dp)
                        )
                            .clickable {
                                stateAdminView = !stateAdminView
                            }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Стат", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = primaryColor
                                )
                            )
                        }
                    }
                }
            }
        }
        if(stateAdminView){
            LazyColumn(Modifier.padding(bottom =islandDp ),horizontalAlignment = Alignment.CenterHorizontally) {
                item{
                    Box(Modifier.padding(top=7.dp, bottom = 7.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
                        Text("События", style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor
                        )
                        )
                    }
                }
                items(spaceTek.events){
                        event->
                    Box(Modifier.padding(top=7.dp, bottom = 7.dp)) {
                        CardsEventPhone(
                            secondColor,
                            primaryColor,
                            event.name,
                            event.date,
                            event.time,
                            event.place
                        )
                    }
                }

                item {
                    Box(Modifier.padding(top=7.dp, bottom = 7.dp).width(300.dp).height(150.dp).background(themeColor,
                        RoundedCornerShape(10)
                    ).clickable {
                        spaceScreenState=3
                    }, contentAlignment = Alignment.Center){
                        Text("Создать", style = TextStyle(
                            fontSize = 24.sp,
                            color= primaryColor
                        )
                        )
                    }
                }
                items(spaceTek.tasks){
                        task->
                    Box(Modifier.padding(top=7.dp, bottom = 7.dp)){
                        CardsTaskPhone(secondColor,primaryColor,task.name,task.status,task.deadline,task.priority)

                    }
                }}


        }else{
            userStata(backgroungColor,primaryColor,secondColor,themeColor)
        }
    }
}





@Composable
fun adminStata( backgroungColor: Color, primaryColor: Color, secondColor: Color, themeColor: Color){
    val server = Reuests()
    var tasks by remember { mutableStateOf(emptyList<StataAdmin>()) }
    val scope = rememberCoroutineScope()
    scope.launch {
        server.getSpaceStatAdmin(spaceTek.id, token, onSuccess ={
                res->tasks=res
        }, onFailure = {
                res->
            println(res)
        })
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Задачи", style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        )
        LazyColumn {
            items(tasks){
                task->
                cardStatAdminPhone(task.name,task.deadline,task.priority,task.attendeeCount,task.doneTaskCount,"${task.avg} ${task.avgLabel}")
            }
        }
    }
}

@Composable
fun userStata( backgroungColor: Color, primaryColor: Color, secondColor: Color, themeColor: Color){
    val server = Reuests()
    var tasks by remember { mutableStateOf(UserStat(0,0,0,0,"",0)) }
    val scope = rememberCoroutineScope()
    scope.launch {
        server.getSpaceStatUser(spaceTek.id, token, onSuccess ={
                res->tasks=res
        }, onFailure = {
                res->
            println(res)
        })
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Задачи", style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        )
        LazyColumn {
            item{

            }
        }
    }
}