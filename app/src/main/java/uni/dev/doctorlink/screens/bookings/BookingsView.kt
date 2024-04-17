package uni.dev.doctorlink.screens.bookings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.component.Dialog
import uni.dev.doctorlink.items.BookingItem
import uni.dev.doctorlink.ui.theme.Blue
import uni.dev.doctorlink.ui.theme.Gray_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingsView(vm: BookingsViewModel) {
    val showActive = vm.showActive.observeAsState().value!!
    val activeBookings = vm.activeBookings.observeAsState().value!!
    val passiveBookings = vm.passiveBookings.observeAsState().value!!
    val loadingActive = vm.loadingActive.observeAsState().value!!
    val loadingPassive = vm.loadingPassive.observeAsState().value!!
    val showDeleteDialog = vm.showDeleteDialog.observeAsState().value!!

    Column(
        Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Card(
                onClick = { vm.showActive() },
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
                modifier = Modifier.width(120.dp),
                colors = CardDefaults.cardColors(if (showActive) Blue else Gray_2)
            ) {
                Text(
                    text = "Kelasi",
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.W600,
                    color = Color.White
                )
            }
            Spacer(
                modifier = Modifier
                    .width(0.5.dp)
                    .background(Color.White)
            )
            Card(
                onClick = { vm.showPassive() },
                shape = RoundedCornerShape(bottomEnd = 12.dp, topEnd = 12.dp),
                modifier = Modifier.width(120.dp),
                colors = CardDefaults.cardColors(if (!showActive) Blue else Gray_2)
            ) {
                Text(
                    text = "O'tgan",
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.W600,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        if (showActive) if (loadingActive) Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        else if (activeBookings.isEmpty()) Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "Ko'riklar yo'q", modifier = Modifier.align(Alignment.Center))
        } else LazyColumn(Modifier.padding(horizontal = 12.dp)) {
            items(activeBookings) {
                BookingItem(booking = it, active = true, forComment = false, onDelete = {vm.openDeleteDialog(it.key!!)}, onMakeCommented = { }, onComment = {})
            }
        }
        else if (loadingPassive) Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else if (passiveBookings.isEmpty()) Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "Ko'riklar yo'q", modifier = Modifier.align(Alignment.Center))
        } else LazyColumn(Modifier.padding(horizontal = 12.dp)) {
            items(passiveBookings) {
                BookingItem(
                    booking = it,
                    active = false,
                    forComment = false,
                    onDelete = { vm.openDeleteDialog(it.key!!) },
                    onMakeCommented = { },
                    onComment = {})
            }
        }
    }
    AnimatedVisibility(visible = showDeleteDialog) {
        Dialog(text = "Tibbiy ko'rikni bekor qilmoqchimisiz?", icon = painterResource(R.drawable.alert), onDismiss = { vm.closeDeleteDialog() }) {
            vm.deleteBooking()
        }
    }
}