//##################################################################################################
//##################################################################################################
//#####################                    Profile Screen                    #######################
//##################################################################################################
//####  Author:KulishovIV                            ###############################################
//####  Version:0.0.1                                ###############################################
//####  Date:23.11.2024                              ###############################################
//##################################################################################################
//##################################################################################################

package ru.pingfly.todoping

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.painterResource
import todoping.composeapp.generated.resources.Res
import todoping.composeapp.generated.resources.elipse
import todoping.composeapp.generated.resources.man
import todoping.composeapp.generated.resources.ok
import todoping.composeapp.generated.resources.vector



var token by mutableStateOf("")
//=====================================================================================
//profile block for desktop
//Input values:
//              name:String - username
//              totalTask:String - total task
//              overdue:Int - overdue task
//              middleTime:Double - middle time for task
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun mainProfileDesctop(name:String, totalTask:String, overdue:Int, middleTime:Double,backgroungColor: Color, primaryColor:Color,themeColor:Color) {

    Box(
        Modifier.fillMaxWidth().height(700.dp).background(backgroungColor),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.padding(end = 110.dp).width(434.dp).height(434.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(painterResource(Res.drawable.elipse), null, modifier = Modifier.scale(2f))
                Image(
                    painter = painterResource(Res.drawable.man), null, modifier = Modifier.scale(1f)
                )

            }
            Box(Modifier.padding(start = 110.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = name, style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor
                        )
                    )
                    Row(
                        Modifier.padding(top = 25.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier.padding(end = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Выполнено", style = TextStyle(
                                    fontSize = 24.sp,
                                    color = primaryColor
                                )
                            )
                            Text(
                                "задач", style = TextStyle(
                                    fontSize = 24.sp,
                                    color = primaryColor
                                )
                            )
                            Box(
                                Modifier.width(180.dp).height(70.dp)
                                    .background(
                                        themeColor,
                                        RoundedCornerShape(10)
                                    ), contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    totalTask, style = TextStyle(
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = primaryColor
                                    )
                                )
                            }
                        }
                        Column(
                            Modifier.padding(start = 15.dp, end = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Просрочено", style = TextStyle(
                                    fontSize = 24.sp,
                                    color = primaryColor
                                )
                            )
                            Text(
                                "задач", style = TextStyle(
                                    fontSize = 24.sp,
                                    color = primaryColor
                                )
                            )
                            Box(
                                Modifier.width(180.dp).height(70.dp)
                                    .background(if (overdue == 0) themeColor else Color(207, 41, 41), RoundedCornerShape(10)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    overdue.toString(), style = TextStyle(
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = primaryColor
                                    )
                                )
                            }
                        }
                        Column(
                            Modifier.padding(start = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Среднее время", style = TextStyle(
                                    fontSize = 24.sp,
                                    color = primaryColor
                                )
                            )
                            Text(
                                "выполнения", style = TextStyle(
                                    fontSize = 24.sp,
                                    color = primaryColor
                                )
                            )
                            Box(
                                Modifier.width(180.dp).height(70.dp)
                                    .background(themeColor, RoundedCornerShape(10)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    middleTime.toString(), style = TextStyle(
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = primaryColor
                                    )
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}


//=====================================================================================
//profile block for desktop
//Input values:
//              name:String - username
//              totalTask:String - total task
//              overdue:Int - overdue task
//              middleTime:Double - middle time for task
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun mainProfilePhone(name:String, totalTask:String, overdue:Int, middleTime:Double,backgroungColor: Color, primaryColor:Color, themeColor:Color){

    Box(Modifier.fillMaxWidth().background(backgroungColor)){
        Box(Modifier.padding(end=0.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
            Image(painterResource(Res.drawable.elipse), null, modifier = Modifier.scale(2f))
            Image(painter = painterResource( Res.drawable.man), null
                , modifier = Modifier.scale(1f))

        }
        Box(Modifier.padding(top=200.dp).fillMaxWidth(), contentAlignment = Alignment.Center){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = name, style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color =primaryColor
                )
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.padding(top=15.dp, start = 15.dp, end = 15.dp, bottom = 25.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Выполнено", style = TextStyle(
                            fontSize = 20.sp,
                            color = primaryColor
                        )
                    )
                    Text(
                        "задач", style = TextStyle(
                            fontSize = 20.sp,
                            color = primaryColor
                        )
                    )
                    Box(
                        Modifier.width(120.dp).height(38.dp)
                            .background(
                                themeColor,
                                RoundedCornerShape(10)
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            totalTask, style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        )
                    }
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                    Column(
                        Modifier.padding(start = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Среднее время", style = TextStyle(
                                fontSize = 20.sp,
                                color = primaryColor
                            )
                        )
                        Text(
                            "выполнения", style = TextStyle(
                                fontSize = 20.sp,
                                color = primaryColor
                            )
                        )
                        Box(
                            Modifier.width(120.dp).height(38.dp)
                                .background(themeColor, RoundedCornerShape(10)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                middleTime.toString(), style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                            )
                        }
                    }


                }
            }
            Row( verticalAlignment = Alignment.CenterVertically){
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                    Column(
                        Modifier.padding(start = 15.dp, end = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Просрочено", style = TextStyle(
                                fontSize = 20.sp,
                                color = primaryColor
                            )
                        )
                        Text(
                            "задач", style = TextStyle(
                                fontSize = 20.sp,
                                color = primaryColor
                            )
                        )
                        Box(
                            Modifier.width(120.dp).height(38.dp)
                                .background(if (overdue == 0) themeColor else Color(207, 41, 41), RoundedCornerShape(10)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                overdue.toString(), style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                            )
                        }
                    }
                }
            }
        }





    }
}


//=====================================================================================
//CompactProfile
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================

@Composable
fun CompactProfile(backgroungColor: Color, primaryColor:Color, secondColor:Color,themeColor:Color){
    var listEvent by remember { mutableStateOf(emptyList<Event>()) }
    var listTask by remember { mutableStateOf(emptyList<Task>()) }
    var name by remember { mutableStateOf("Igor") }
    var totalTask by remember { mutableStateOf("0/25") }
    var overdue by remember { mutableStateOf(5) }
    var middleTime by remember { mutableStateOf(3.5) }
    val server = Reuests()

    val scope = rememberCoroutineScope()
    scope.launch {
        while(token==""){
            delay(100)
        }
        server.getUserProfile(token,onSuccess = { user->
            name=user.name
            listTask=user.tasks
            listEvent=user.events
            var count by mutableStateOf(0)
            var countOverdue by mutableStateOf(0)
            for(x in listTask){
                if(x.status=="сделано") count++
            }
            totalTask="$count/${listTask.size}"
            overdue=countOverdue


        }, onFailure = {error -> println(error) })
    }
    Scaffold {
        Column() {
            //shapkaPhone()
            LazyColumn {
                item {
                    mainProfilePhone(name, totalTask, overdue, middleTime, backgroungColor, primaryColor, themeColor)
                }
                item {
                    Box(
                        Modifier.fillMaxWidth().height(254.dp).background(backgroungColor)
                    ) {
                        Column {
                            Text(
                                "События", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = primaryColor
                                ), modifier = Modifier.padding(top = 25.dp, start = 25.dp)
                            )

                            LazyRow(Modifier.padding(start = 25.dp, top = 25.dp), contentPadding = PaddingValues(15.dp)) {
                                items(listEvent){
                                    event-> CardsEventPhone(secondColor,primaryColor,event.name,event.date.toString(),event.time.toString(),event.place)
                                }
                            }

                        }
                    }
                }
                item {
                    Box(
                        Modifier.fillMaxWidth().height(254.dp).background(backgroungColor)
                    ) {
                        Column {
                            Text(
                                "Задачи", style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = primaryColor
                                ), modifier = Modifier.padding(top = 25.dp, start = 25.dp)
                            )

                            LazyRow(Modifier.padding(start = 25.dp, top = 25.dp), contentPadding = PaddingValues(15.dp)) {
                                items(listTask){
                                        task-> CardsTaskPhone(secondColor,primaryColor,task.name,task.status,task.deadline,task.priority)
                                }
                            }
                        }
                    }
                }

                item {
                    Box(
                        Modifier.fillMaxWidth().height(100.dp).background(backgroungColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Row {
                            Text(
                                "todo.", style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = themeColor
                                )
                            )
                            Text(
                                ".stat", style = TextStyle(
                                    fontSize = 24.sp,
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
    //}

}


//=====================================================================================
//profile block for desktop
//Input values:
//              backgroungColor:Color - backgroungColor
//              primaryColor:Color - primaryColor
//              secondColor:Color - secondColor
//              themeColor:Color - themeColor
//=====================================================================================
@Composable
fun DesctopProfile(backgroungColor: Color, primaryColor:Color, secondColor:Color,themeColor:Color){
    var listEvent by remember { mutableStateOf(emptyList<Event>()) }
    var listTask by remember { mutableStateOf(emptyList<Task>()) }
    var name by remember { mutableStateOf("Igor") }
    var totalTask by remember { mutableStateOf("0/25") }
    var overdue by remember { mutableStateOf(5) }
    var middleTime by remember { mutableStateOf(3.5) }
    val server = Reuests()
    val scope = rememberCoroutineScope()
    scope.launch {
        server.getUserProfile(token,onSuccess = { user->
            name=user.name
            listTask=user.tasks
            listEvent=user.events

        }, onFailure = {error -> println(error) })
    }
    /*var whoami = WhoamiRequest("Rowan Petrov", 1,10, emptyList(), emptyList(), emptyList(),
        emptyList(), emptyList(), emptyList()
    )
    if(key!=null){
        val newWhoami = server.whoamiRequest(key)
        if(newWhoami!=null) whoami=newWhoami
    }
    if(whoami.myTop!=null){
        for(x in whoami.myTop){
            if(key!=null) {
                var top = server.getTop(key, x)
                if(top!=null) listMyTop+=top
            }
        }
    }
    if(whoami.inTop!=null){
        for(x in whoami.inTop){
            if(key!=null) {
                var top = server.getTop(key, x)
                if(top!=null) listFriendTop+=top
            }
        }
    }
    if(whoami.myThree!=null){

    }
    if(whoami.inThree!=null){

    }
    if(whoami.myAward!=null){

    }
    if(whoami.inAward!=null){

    }

     */

    LazyColumn() {
        item {
            mainProfileDesctop(name, totalTask, overdue, middleTime, backgroungColor, primaryColor, themeColor)
        }
        item {
            Box(Modifier.fillMaxWidth().height(474.dp).background(backgroungColor)){
                Column {
                    Text("Tops", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        color = primaryColor
                    ), modifier = Modifier.padding(top=50.dp,start=100.dp))
                    /*
                    LazyRow(Modifier.padding(start=100.dp, top=80.dp)) {
                        if(whoami.myTop!=null) {
                            items(listMyTop) { myTop ->
                                Box(Modifier.clickable {
                                    topActive=myTop.id
                                    state=11
                                }) {
                                    gameItemDesktop(
                                        myTop.name,
                                        myTop.description,
                                        darkTheme.onPrimary
                                    )
                                }
                            }
                        }
                        if(whoami.inTop!=null) {
                            items(listFriendTop) { intop ->
                                Box(Modifier.clickable {
                                    topActive=intop.id
                                    state=11
                                }) {
                                    gameItemDesktop(
                                        intop.name,
                                        intop.description,
                                        Color(122, 122, 122)
                                    )
                                }
                            }
                        }
                        item {
                            val nC = Color(
                                darkTheme.secondary.red, darkTheme.secondary.green, darkTheme.secondary.blue,
                                alpha = 0.5f
                            )
                            Box(Modifier.width(400.dp).height(200.dp).background(nC, shape = RoundedCornerShape(10)),
                                contentAlignment = Alignment.Center){
                                Text("Add", style = TextStyle(
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                    color =  darkTheme.primary
                                )
                                )
                            }
                        }
                    }*/

                }
            }
        }
        item {
            Box(Modifier.fillMaxWidth().height(474.dp).background(backgroungColor)){
                Column {
                    Text("Threes", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        color = primaryColor
                    ), modifier = Modifier.padding(top=50.dp,start=100.dp))

                    /*LazyRow(Modifier.padding(start=100.dp, top=80.dp)) {
                        if(whoami.myThree!=null) {
                            items(listMyThree) { myTop ->
                                gameItemDesktop(myTop.name,myTop.description, darkTheme.onPrimary)
                            }
                        }
                        if(whoami.inThree!=null) {
                            items(listFriendThree) { intop ->
                                gameItemDesktop(intop.name,intop.description, Color(122,122,122))
                            }
                        }
                        item {
                            val nC = Color(
                                darkTheme.secondary.red, darkTheme.secondary.green, darkTheme.secondary.blue,
                                alpha = 0.5f
                            )
                            Box(Modifier.width(400.dp).height(200.dp).background(nC, shape = RoundedCornerShape(10)),
                                contentAlignment = Alignment.Center){
                                Text("Add", style = TextStyle(
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                    color =  darkTheme.primary
                                )
                                )
                            }
                        }
                    }*/

                }
            }
        }

    }
}
@Serializable
data class Key(
    val accessToken:String,
    val refreshToken:String
)
@Serializable
data class AuthRequest(val login:String, val password:String)



// Data models
@Serializable
data class UserProfile(
    val id: Int,
    val name: String,
    val events: List<Event>,
    val tasks: List<Task>,
    val doneTaskCount: Int,
    val lateTaskCount: Int,
    val allTaskCount: Int,
    val avgTime: Int,
    val avgLabel: String
)

@Serializable
data class Event(
    val id: Int,
    val name: String,
    val date: String,
    val time: String,
    val place: String
)

@Serializable
data class Task(
    val id: Int,
    val name: String,
    val deadline: String,
    val status: String,
    val priority: String
)


val baseurl ="https://da93-95-174-102-182.ngrok-free.app"
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//RequestClass
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Reuests{
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })

        }
    }

    suspend fun auth(onSuccess: (Key)->Unit, onFailure: (String)->Unit) {

        val response = client.post("$baseurl/auth"){
            contentType(ContentType.Application.Json)
            setBody(AuthRequest("login","password"))
        }
        if(response.status== HttpStatusCode.OK){
            val resp:Key = response.body()
            onSuccess(resp)
        }else{
            onFailure("Error: ${response.status}")
        }

    }
    suspend fun getUserProfile(accessToken: String, onSuccess: (UserProfile) -> Unit, onFailure: (String) -> Unit) {

        val response = client.get("$baseurl/users/me") {
            header("Authorization", "Bearer $accessToken")
        }

        if (response.status == HttpStatusCode.OK) {
            val userProfile: UserProfile = response.body()
            onSuccess(userProfile)
        } else {
            onFailure("Error: ${response.status}")
        }
    }

}
