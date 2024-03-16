package uni.dev.doctorlink.screens.telegramUser

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Blue
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_3
import uni.dev.doctorlink.ui.theme.Red
import uni.dev.doctorlink.ui.theme.Red_2
import uni.dev.doctorlink.ui.theme.Text2
import uni.dev.doctorlink.ui.theme.Text2_2

@Composable
fun TelegramUserView(vm: TelegramUserViewModel) {
    val username = vm.username.observeAsState().value!!
    val usernameError = vm.usernameError.observeAsState().value!!
    val usernameErrorText = vm.usernameErrorText.observeAsState().value!!
    val dialogOpen = vm.dialogOpen.observeAsState().value!!

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
        .padding(horizontal = 32.dp)) {
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
            modifier = Modifier.padding(horizontal = 24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {
                vm.updateUsername(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Gray_3,
                focusedBorderColor = Primary_3,
                errorBorderColor = Red_2
            ),
            isError = usernameError,
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
            placeholder = { Text(text = "aliyev123", color = Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            TextButton(
                onClick = {
                    vm.sendCode()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
            ) {
                Text(text = "Kod yuborish", fontWeight = FontWeight.W700, fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(6.dp))
            AnimatedVisibility(visible = usernameError) {
                if (usernameError) Text(
                    text = usernameErrorText,
                    color = Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
    AnimatedVisibility(visible = dialogOpen) {
        AlertDialog(onDismissRequest = { vm.closeDialog() }, confirmButton = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 16.dp)) {
                Icon(painterResource(id = R.drawable.telegram), contentDescription = "", modifier = Modifier.size(96.dp), tint = Blue)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Tasdiqlash kodini olish uchun botga start bering", fontWeight = FontWeight.W400, textAlign = TextAlign.Center, fontSize = 18.sp, color = Text2_2)
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = { vm.openTelegram() }, colors = ButtonDefaults.textButtonColors(containerColor = Blue), shape = RoundedCornerShape(16.dp)) {
                    Text(text = "Telegramni ochish", color = Color.White, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }, containerColor = Color.White, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp))
    }
}
