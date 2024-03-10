package uni.dev.doctorlink.screens.phoneNumber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_3
import uni.dev.doctorlink.ui.theme.Red
import uni.dev.doctorlink.ui.theme.Text2

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelegramUserView() {
    val telegramUsername = remember {
        mutableStateOf("")
    }
    val telegramUsernameError = remember {
        mutableStateOf(false)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(0.8f))
        Icon(
            painter = painterResource(id = R.drawable.telegram),
            contentDescription = "",
            tint = Gray_2,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Tasdiqlash kodini olish uchun telegram username kiriting",
            color = Gray,
            modifier = Modifier.padding(horizontal = 42.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = telegramUsername.value,
            onValueChange = {
                if (it.length <= 32) {
                    telegramUsername.value = it
                }
                if (telegramUsernameError.value) telegramUsernameError.value = false
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Gray_3,
                focusedBorderColor = Primary_3
            ),
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.at_sign),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(18.dp),
                    tint = Text2
                )
            },
            placeholder = { Text(text = "aliyev123", color = Gray)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            if (telegramUsernameError.value) Text(
                text = "Username 5-32 uzunlikdagi raqam va harflardan iborat bo'lishi kerak",
                color = Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            ElevatedButton(
                onClick = {
                    telegramUsernameError.value = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 0.dp
                ),
            ) {
                Text(text = "Kod yuborish", fontWeight = FontWeight.SemiBold)
            }
        }
    }

}
