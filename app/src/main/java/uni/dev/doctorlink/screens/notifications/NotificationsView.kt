package uni.dev.doctorlink.screens.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.items.BookingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsView(vm: NotificationsViewModel) {
    val uncommentedBookings = vm.uncommentedBookings.observeAsState().value!!
    val loading = vm.loading.observeAsState().value!!
    Column(Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(title = { Text(text = "Bildirishnomalar", fontSize = 18.sp) }, navigationIcon = {
            IconButton(onClick = { vm.onBack() }) {
                Icon(painterResource(id = R.drawable.arrow_back), contentDescription = "")
            }
        })
        if (loading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else if (uncommentedBookings.isEmpty()) Box(Modifier.fillMaxSize()) {
            Text(
                text = "Bildirishnomalar yo'q", modifier = Modifier.align(Alignment.Center), color = Color.Gray
            )
        } else LazyColumn(Modifier.padding(horizontal = 8.dp)) {
            items(uncommentedBookings){
                BookingItem(booking = it, active = false, forComment = true, onDelete = {  }, onMakeCommented = {vm.makeCommented(it.key!!)}) {
                    vm.goToComment(it)
                }
            }
        }
    }
}