package uni.dev.doctorlink.screens.phoneNumber

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import uni.dev.doctorlink.component.PhoneVisualTransformation
import uni.dev.doctorlink.component.ProgressIndicator
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_3
import uni.dev.doctorlink.ui.theme.Red
import uni.dev.doctorlink.ui.theme.Red_2
import uni.dev.doctorlink.ui.theme.Text2

@Composable
fun PhoneNumberView(vm: PhoneNumberViewModel) {
    val phone = vm.phone.observeAsState().value!!
    val phoneError = vm.usernameError.observeAsState().value!!
    val loading = vm.loading.observeAsState().value!!

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.8f))
        Icon(
            Icons.Rounded.Phone,
            contentDescription = "",
            tint = Gray_2,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Tasdiqlash kodini olish uchun telefon raqam kiriting",
            color = Gray,
            modifier = Modifier.padding(horizontal = 24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = {
                vm.updatePhone(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Gray_3,
                focusedBorderColor = Primary_3,
                errorBorderColor = Red_2,
                focusedTextColor = Text2,
                unfocusedTextColor = Text2
            ),
            isError = phoneError,
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable._998),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 12.dp)
                        .size(36.dp),
                    tint = Text2
                )
            },
            placeholder = { Text(text = "-- --- -- --", color = Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(fontSize = 18.sp),
            visualTransformation = PhoneVisualTransformation("00 000 00 00", '0')
        )
        Spacer(modifier = Modifier.height(6.dp))
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            TextButton(
                onClick = {
                    vm.sendCode()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(text = "Kod yuborish", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(6.dp))
            AnimatedVisibility(visible = phoneError) {
                if (phoneError) Text(
                    text = "Telefon raqami noto'g'ri kiritilgan",
                    color = Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
    AnimatedVisibility(visible = loading) {
        ProgressIndicator()
    }
}
