package uni.dev.doctorlink.screens.appointment

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.component.Dialog
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_2
import uni.dev.doctorlink.ui.theme.Text
import uni.dev.doctorlink.ui.theme.Text_2
import uni.dev.doctorlink.util.Api
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ResourceAsColor")
@Composable
fun AppointmentView(vm: AppointmentViewModel) {
    val showExitDialog = vm.showExitDialog.observeAsState().value!!
    val showCompletedDialog = vm.showCompleteDialog.observeAsState().value!!
    val time = vm.time.observeAsState().value!!

    BackHandler {
        vm.openExitDialog()
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Qabulga yozilish",
                fontSize = 20.sp,
                color = Text_2,
                fontWeight = FontWeight.W700
            )
        }, navigationIcon = {
            IconButton(onClick = { vm.openExitDialog() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back), contentDescription = ""
                )
            }
        })

        Column(Modifier.weight(1f)) {
            DateSelector(vm)
            Spacer(modifier = Modifier.height(20.dp))
            TimeSelector(vm)
        }

        TextButton(
            onClick = { vm.onConfirm() },
            enabled = time.isNotBlank(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.textButtonColors(Primary, disabledContainerColor = Primary_2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = "Tasdiqlash", color = Color.White)
        }
    }
    AnimatedVisibility(visible = showExitDialog) {
        Dialog(text = "Chiqmoqchimisiz",
            icon = painterResource(id = R.drawable.alert),
            onDismiss = { vm.closeExitDialog() }) {
            vm.navigateBack()
        }
    }
    AnimatedVisibility(visible = showCompletedDialog) {
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
                Text(text = "Ro'yhatdan o'tish muvaffaqiyatli yakunlandi", fontSize = 18.sp, textAlign = TextAlign.Center, color = Text)
            }
        })
    }
}

@Composable
fun DateSelector(vm: AppointmentViewModel) {
    val dateSelected = vm.date.observeAsState().value
    val daysAheadCount = 10

    Text(
        text = "Kunni tanlang",
        Modifier.padding(start = 16.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(6.dp))
    val today = LocalDate.now().plusDays(1.toLong())
    LazyRow(contentPadding = PaddingValues(horizontal = 6.dp)) {
        items(daysAheadCount) {
            val day = today.plusDays(it.toLong())
            val isSelected = dateSelected!!.isEqual(day)
            val carDefaultElevation = if (isSelected) 0.dp else 1.dp
            Card(
                onClick = { vm.selectDate(day) },
                modifier = Modifier.padding(4.dp),
                border = BorderStroke(width = .5.dp, color = Primary),
                colors = CardDefaults.cardColors(if (isSelected) Primary else Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = carDefaultElevation,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)
                ) {
                    val text1Color = if (isSelected) Color.White else Text_2
                    val text2Color = if (isSelected) Color(1f, 1f, 1f, .8f) else Gray
                    Text(
                        text = Api.getDay(day.dayOfWeek.value),
                        color = text1Color,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = day.dayOfMonth.toString() + "-" + Api.getMonth(day.monthValue),
                        color = text2Color,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TimeSelector(vm: AppointmentViewModel) {
    val unavailableTimes = vm.unavailableTimes.observeAsState().value!!
    Text(
        text = "Vaqtni tanlang",
        Modifier.padding(start = 16.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(6.dp))
    val selectedTime = vm.time.observeAsState().value!!
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(Api.getAppointmentTimes()) { time ->
            val disabled = unavailableTimes.contains(time)
            val isSelected = selectedTime == time
            val carDefaultElevation = if (isSelected) 0.dp else 1.dp
            Card(
                enabled = !disabled,
                onClick = { vm.selectTime(time) },
                modifier = Modifier.padding(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) Primary else Color.White,
                    disabledContainerColor = Gray_3
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = carDefaultElevation,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp,
                    disabledElevation = 0.dp

                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 10.dp)
                        .fillMaxWidth()
                ) {
                    val textColor = if (disabled) Gray else if (isSelected) Color.White else Text_2
                    Text(
                        text = time,
                        color = textColor,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                }
            }
        }

    }

}

