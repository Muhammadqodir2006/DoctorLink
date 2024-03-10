package uni.dev.doctorlink.screens.smsCode

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Red
import uni.dev.doctorlink.ui.theme.Text2
import uni.dev.doctorlink.ui.theme.Text2_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmsCodeView() {
    val codeLength = 5
    val username = remember {
        mutableStateOf("@aliyev123")
    }
    val code = remember {
        mutableStateOf("")
    }
    val codeError = remember {
        mutableStateOf(false)
    }
    var min = 3
    var sec = 0
    val time = remember {
        mutableStateOf("$min:00")
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            if (sec > 0) sec-=1
            else {
                if (min > 0) {
                    min-=1
                    sec = 59
                } else {
                    // TODO: GO BACK
//                navController.back()
                }
            }
            time.value = if (sec > 10) "${min}:${sec}"
            else "$min:0$sec"
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
//            .background(Color.White)
    ) {
        TopAppBar(title = { }, navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painterResource(id = R.drawable.arrow_back),
                    contentDescription = "",
                    tint = Text2
                )
            }
        })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sms_code_demo),
                contentDescription = "",
                tint = Gray_2,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Telegram bot orqali ${username.value} ga yuborilgan tasdiqlash kodini kiriting",
                color = Gray,
                modifier = Modifier.padding(horizontal = 42.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                for (index in 0 until codeLength) {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .width(60.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        border = BorderStroke(
                            1.5.dp, if (codeError.value) Red else Gray_3
                            //                                Color(                                0xFFE7E7E7)
                        ),
                        shape = RoundedCornerShape(16.dp),
//                        elevation = CardDefaults.cardElevation(3.dp)
                    ) {
                        Text(
                            text = if (code.value.length > index) code.value[index].toString() else "5",
                            fontSize = 36.sp,
                            modifier = Modifier
                                .padding(vertical = 18.dp)
                                .align(Alignment.CenterHorizontally),
                            color = if (code.value.length > index) Text2 else Color.Transparent
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = time.value,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
//            Spacer(modifier = Modifier.height(12.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 1 until 4) {
                    NumberButton(i.toString(), false, code, codeLength)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 4 until 7) {
                    NumberButton(i.toString(), false, code, codeLength)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 7 until 10) {
                    NumberButton(i.toString(), false, code, codeLength)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                NumberButton("", false, code, codeLength)
                NumberButton("0", false, code, codeLength)
                NumberButton("1", true, code, codeLength)
            }
        }
    }
}

@Composable
fun NumberButton(num: String, b: Boolean, code: MutableState<String>, codeLength: Int) {
    Button(
        onClick = {
            if (b) {
                if (code.value.isNotEmpty()) code.value = code.value.dropLast(1)
            } else {
                if (code.value.length < codeLength) code.value += num
            }
        },
        modifier = Modifier
            .fillMaxHeight()
            .clip(CircleShape)
            .aspectRatio(1f)
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Gray_4,
            disabledContainerColor = Color.Transparent
        ),
        enabled = num != ""
    ) {
        if (!b) Text(
            text = num,
            color = Text2_2,
            fontSize = 20.sp,
        ) else {
            Icon(
                painterResource(id = R.drawable.backspace_fill),
                contentDescription = "",
                tint = Text2_2
            )
        }
    }
}
