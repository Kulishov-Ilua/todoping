package ru.pingfly.todoping

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

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
    suspend fun getMySpaces(accessToken: String, onSuccess: (List<miniSpace>) -> Unit, onFailure: (String) -> Unit) {

        val response = client.get("$baseurl/spaces/my") {
            header("Authorization", "Bearer $accessToken")
        }

        if (response.status == HttpStatusCode.OK) {
            val listSpace:List<miniSpace> = response.body()
            onSuccess(listSpace)
        } else {
            onFailure("Error: ${response.status}")
        }
    }
    suspend fun postSpaces(accessToken: String, space:SpaceCreate ) {

        val response = client.post("$baseurl/spaces") {
            header("Authorization", "Bearer $accessToken")
            contentType(ContentType.Application.Json)
            setBody(space)

        }
        println(response.status.description)
    }

}

@Serializable
data class miniSpace(val id:Int, val name:String, val color:String)

@Serializable
data class SpaceCreate(val name:String, val desciption:String, val color:String, val isPersonal:Boolean)