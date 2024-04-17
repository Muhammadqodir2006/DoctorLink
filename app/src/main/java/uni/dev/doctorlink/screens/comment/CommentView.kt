package uni.dev.doctorlink.screens.comment

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.component.Dialog
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_2
import uni.dev.doctorlink.ui.theme.Text2

@Composable
fun CommentView(vm: CommentViewModel) {
    val rating = vm.rating.observeAsState().value!!
    val text = vm.text.observeAsState().value!!
    val showExitDialog= vm.exitDialogOpen.observeAsState().value!!
    val showSuccessDialog = vm.successDialogOpen.observeAsState().value!!


    BackHandler {
        vm.onBack()
    }
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp)
        ) {
            IconButton(onClick = { vm.onBack() }) {
                Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "")
            }
        }
        Card(colors = CardDefaults.cardColors(Gray_4)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Doktorni baholang", fontSize = 18.sp, color = Text2)
                Spacer(modifier = Modifier.height(4.dp))
                Row(Modifier.padding(horizontal = 32.dp)) {
                    for (i in 1..5) {
                        val res = if (i <= rating) R.drawable.star_fill else R.drawable.star_outline
                        IconButton(onClick = { vm.changeRating(i) }) {
                            Icon(
                                painter = painterResource(res), contentDescription = "", tint = Color(0xFFFFD700), modifier = Modifier.size(42.dp)
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(24.dp))

            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { vm.changeText(it) },
            shape = RoundedCornerShape(12.dp),
            minLines = 4,
            maxLines = 10,
            placeholder = {
                Text(
                    text = "Izoh yozing...", color = Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Gray, focusedBorderColor = Primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 42.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        TextButton(
            enabled = text.isNotBlank(),
            onClick = { vm.onConfirmRate() },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.textButtonColors(Primary, disabledContainerColor = Primary_2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        ) {
            Text(text = "Tasdiqlash", color = Color.White)
        }

    }
    AnimatedVisibility(visible = showExitDialog) {
        Dialog(text = "Chiqmoqchimisiz?", icon = painterResource(id = R.drawable.alert), onDismiss = { vm.closeExitDialog() }) {
            vm.navigateBack()
        }
    }
    AnimatedVisibility(visible = showSuccessDialog) {
        AlertDialog(onDismissRequest = { }, confirmButton = {
            TextButton(
                onClick = { vm.navigateBack() },
                colors = ButtonDefaults.textButtonColors(containerColor = Primary),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Ok", color = Color.White)
            }
        }, text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(32.dp))
                Icon(
                    painter = painterResource(id = R.drawable.success),
                    contentDescription = "",
                    tint = Primary,
                    modifier = Modifier.size(84.dp)
                )
                Text(text = "Ro'yhatdan o'tish muvaffaqiyatli yakunlandi", fontSize = 18.sp, textAlign = TextAlign.Center, color = uni.dev.doctorlink.ui.theme.Text)
            }
        })
    }


}