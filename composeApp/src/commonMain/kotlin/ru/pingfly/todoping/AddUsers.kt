package ru.pingfly.todoping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import todoping.composeapp.generated.resources.Res
import todoping.composeapp.generated.resources.man
import todoping.composeapp.generated.resources.vector
@Composable
fun AddUsers(backgroundColor: Color, textColor: Color, PrimaryColor: Color, SecondaryColor: Color){
    Box(Modifier.fillMaxSize().background(backgroundColor)){
        Column {
            Box(Modifier.padding(top = 20.dp)) {
                Text(text = "Участники", style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Box(Modifier.padding(25.dp).background(PrimaryColor,  RoundedCornerShape(10))) {
                Text(
                    text = "Добавить",
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    ),
                    modifier = Modifier.fillMaxWidth().height(70.dp).wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
            Box(Modifier.padding(start = 25.dp, end = 25.dp).background(SecondaryColor,  RoundedCornerShape(10))) {
                Row (modifier = Modifier.fillMaxWidth().height(80.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)){
                    Image(
                        painter = painterResource(Res.drawable.man),
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                            .border(3.dp, Color.White, RoundedCornerShape(15.dp))
                    )
                    Text(
                        text = "Вы",
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )

                    )
                }
            }

        }
    }
}