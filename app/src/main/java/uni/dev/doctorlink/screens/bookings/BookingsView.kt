package uni.dev.doctorlink.screens.bookings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.items.BookingItem
import uni.dev.doctorlink.ui.theme.Blue
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Text_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingsView(vm: BookingsViewModel) {
    val filter = vm.filter.observeAsState().value!!
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = {
            Text(
                text = "Tibbiy ko'riklar",
                fontSize = 20.sp,
                color = Text_2,
                fontWeight = FontWeight.W700
            )
        })

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Card(
                onClick = { vm.updateFilter(1) },
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
                modifier = Modifier.width(120.dp),
                colors = CardDefaults.cardColors(if (filter == 1) Blue else Gray_2)
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
                onClick = { vm.updateFilter(2) },
                shape = RoundedCornerShape(bottomEnd = 12.dp, topEnd = 12.dp),
                modifier = Modifier.width(120.dp),
                colors = CardDefaults.cardColors(if (filter == 2) Blue else Gray_2)
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
        LazyColumn(Modifier.padding(horizontal = 12.dp)) {
            items(10) {
                BookingItem()
            }
        }
    }
}