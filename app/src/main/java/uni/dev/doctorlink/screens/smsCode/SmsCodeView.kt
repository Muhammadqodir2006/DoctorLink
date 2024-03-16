package uni.dev.doctorlink.screens.smsCode

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uni.dev.doctorlink.R
import uni.dev.doctorlink.component.ExitDialog
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Red
import uni.dev.doctorlink.ui.theme.Text2
import uni.dev.doctorlink.ui.theme.Text2_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmsCodeView(navController: NavHostController, vm: SmsCodeViewModel) {
    val code = vm.code.observeAsState().value!!
    val codeError = vm.codeError.observeAsState().value!!
    val dialogOpen = vm.dialogOpen.observeAsState().value!!
    val borderColor = animateColorAsState(
        targetValue = if (codeError) Red else Gray_3,
        animationSpec = tween(300),
        finishedListener = {
            if (codeError) vm.updateCodeError()
        },
        label = ""
    )

    BackHandler {
        if (dialogOpen) vm.closeDialog()
        else vm.openDialog()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(title = { }, navigationIcon = {
            IconButton(onClick = {
                vm.openDialog()
            }) {
                Icon(
                    painterResource(id = R.drawable.arrow_back),
                    contentDescription = "",
                    tint = Text2
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White))
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
                text = "Telegram bot orqali ${vm.username} ga yuborilgan tasdiqlash kodini kiriting",
                color = Gray,
                modifier = Modifier.padding(horizontal = 42.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                for (index in 0 until vm.codeLength) {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .width(60.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        border = BorderStroke(
                            1.5.dp, color = borderColor.value
                            //                                Color(                                0xFFE7E7E7)
                        ),
                        shape = RoundedCornerShape(16.dp),
//                        elevation = CardDefaults.cardElevation(3.dp)
                    ) {
                        Text(
                            text = if (code.length > index) code[index].toString() else "5",
                            fontSize = 36.sp,
                            modifier = Modifier
                                .padding(vertical = 18.dp)
                                .align(Alignment.CenterHorizontally),
                            color = if (code.length > index) Text2 else Color.Transparent
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
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
                    NumberButton(i.toString(), false, code, vm.codeLength, vm)
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
                    NumberButton(i.toString(), false, code, vm.codeLength, vm)
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
                    NumberButton(i.toString(), false, code, vm.codeLength, vm)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                NumberButton("", false, code, vm.codeLength, vm)
                NumberButton("0", false, code, vm.codeLength, vm)
                NumberButton("1", true, code, vm.codeLength, vm)
            }
        }
    }
    AnimatedVisibility(visible = dialogOpen) {
        ExitDialog(
            onDismiss = { vm.closeDialog() },
            onConfirm = { navController.popBackStack() }
        )
    }
}

@Composable
fun NumberButton(
    num: String,
    backSpace: Boolean,
    code: String,
    codeLength: Int,
    vm: SmsCodeViewModel
) {
    Button(
        onClick = {
            if (backSpace) {
                if (code.isNotEmpty()) vm.updateCode(code.dropLast(1))
            } else {
                if (code.length < codeLength) vm.updateCode(code + num)
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
        if (!backSpace) Text(
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
