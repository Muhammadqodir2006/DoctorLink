package uni.dev.doctorlink.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.model.Booking
import uni.dev.doctorlink.model.Doctor
import uni.dev.doctorlink.ui.theme.Blue
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Red
import uni.dev.doctorlink.ui.theme.Text
import uni.dev.doctorlink.ui.theme.Text2
import uni.dev.doctorlink.ui.theme.Text2_2
import uni.dev.doctorlink.util.Api

//@Preview
//@Composable
//fun ShowBookinsDemo() {
//    Column(
//        Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        Spacer(modifier = Modifier.height(42.dp))
//        Text(
//            text = "Bookinglar",
//            fontSize = 24.sp,
//            color = Text,
//            fontWeight = FontWeight.W700,
//            modifier = Modifier.padding(start = 36.dp)
//        )
//        LazyColumn() {
//            items(3) {
//                BookingItem()
//            }
//        }
//    }
//}

@Composable
fun BookingItem(booking: Booking, active: Boolean, forComment: Boolean, onDelete: () -> Unit, onMakeCommented: () -> Unit, onComment: () -> Unit) {
    val doctor = remember {
        mutableStateOf(Doctor(name = "", surname = "", specialty = listOf(), price = 0))
    }
    val specialties = remember {
        mutableStateOf("")
    }
    Api.getDoctor(booking.doctorKey!!) { doc ->
        doctor.value = doc
        var specs = ""
        doctor.value.specialty!!.forEach { specs += Api.getSpecialty(it).name!! }
    }
    Card(shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0, 0, 0, 5)),
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 4.dp),
        onClick = {}) {
        Column(Modifier.padding(start = 12.dp, end = 12.dp, top = 20.dp, bottom = 16.dp)) {
            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.Person, contentDescription = "", modifier = Modifier.size(16.dp), tint = Text2_2
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = doctor.value.name + " " + doctor.value.surname, fontWeight = FontWeight.W900, color = Text, fontSize = 15.sp
                        )
                        Text(
                            text = " - ",
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = Text2_2,
                        )
                        Text(
                            text = specialties.value,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = Text2_2,
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.DateRange, contentDescription = "", modifier = Modifier.size(16.dp), tint = Text2_2
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = booking.date!!, fontWeight = FontWeight.W900, color = Text2, fontSize = 15.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(id = R.drawable.clock), contentDescription = "", modifier = Modifier.size(16.dp), tint = Text2_2
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = booking.time!!, fontWeight = FontWeight.W900, color = Text2_2, fontSize = 15.sp
                        )
                    }
                }
                Row {
//                    Icon(Icons.Rounded.Edit,
//                        contentDescription = "",
//                        tint = Gray_2,
//                        modifier = Modifier
//                            .size(24.dp)
//                            .clip(CircleShape)
//                            .clickable { }
//                            .padding(3.dp)
//
//                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    if (active) Icon(painterResource(id = R.drawable.cancel_fill),
                        contentDescription = "",
                        tint = Gray_2,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .clickable { onDelete() })
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 16.dp)
                    .padding(horizontal = 16.dp), thickness = 1.dp, color = Gray_2
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (!forComment) Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = if (active) "Endi" else "O'tgan", color = if (active) Blue else Primary, fontSize = 14.sp, fontWeight = FontWeight.W600
                    )
                }
                Row {
                    Spacer(modifier = Modifier.width(4.dp))
                    val price = doctor.value.price
                    Text(
                        "${price.toString().chunked(3).joinToString(separator = " ")}.ch so'm",
                        color = Blue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500
                    )
                }
                if (forComment) {
                    Row {
                        TextButton(
                            onClick = { onMakeCommented() }, shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.textButtonColors(Gray_2 )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete),
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        TextButton(onClick = { onComment() }, shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.textButtonColors(Primary)) {
                            Text(text = "Izoh qoldirish", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}