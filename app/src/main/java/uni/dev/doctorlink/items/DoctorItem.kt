package uni.dev.doctorlink.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import uni.dev.doctorlink.R
import uni.dev.doctorlink.model.Doctor
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2_2
import uni.dev.doctorlink.ui.theme.Text2_3
import uni.dev.doctorlink.ui.theme.Text_2
import uni.dev.doctorlink.util.Api

@Composable
fun DoctorItem(doctor: Doctor, userKey: String, navController: NavController, showHospitalLocation: Boolean) {
    val savedIconResource = remember {
        mutableIntStateOf(R.drawable.saved_outline)
    }
    Api.isSaved(userKey, doctor.key!!, true) {
        if (it) savedIconResource.intValue = R.drawable.saved_fill
    }
    val clinic = remember {
        mutableStateOf("")
    }
    val clinicLocation = remember {
        mutableStateOf("")
    }

    Api.getHospital(doctor.hospitalKey!!) {
//        Log.d("TAG", "DoctorItem: $it")
        clinic.value = it.name!!
        clinicLocation.value = Api.getRegion(it.regionId!!).name!!
    }





    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 4.dp),
        onClick = {
            navController.navigate("doctor_profile/${doctor.key}")
        }
    ) {
        Column(Modifier.padding(vertical = 20.dp, horizontal = 16.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = doctor.image,
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    error = painterResource(id = R.drawable.error),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = doctor.name!! + " " + doctor.surname!!,
                        fontWeight = FontWeight.W800,
                        color = Text_2,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    LazyRow {
                        items(doctor.specialty!!.size) {
//                            Log.d("TAG", "errorCheck : ${doctor.specialty!!.size} , $it")
                            val specialty = Api.getSpecialty(doctor.specialty!![it]).name!!
                            Text(
                                text = specialty,
                                fontWeight = FontWeight.W600,
                                fontSize = 15.sp,
                                color = Text2_2,
                            )
                            if (it != doctor.specialty!!.size - 1) Text(
                                text = ", ",
                                fontWeight = FontWeight.W600,
                                fontSize = 15.sp,
                                color = Text2_2,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = clinic.value,
                        fontWeight = FontWeight.W800,
                        fontSize = 13.sp,
                        color = Primary,
                    )
                    if (showHospitalLocation) Text(
                        text = "(${clinicLocation.value})",
                        fontWeight = FontWeight.W500,
                        fontSize = 12.sp,
                        color = Text2_2,
                    )
                }


                IconButton(onClick = {
                    Api.savedDoctorUpdate(userKey, doctor.key!!) {
                        savedIconResource.intValue = if (it) R.drawable.saved_fill
                        else R.drawable.saved_outline
                    }
                }) {
                    Icon(
                        painterResource(id = savedIconResource.intValue),
                        contentDescription = "",
                        tint = Gray_2
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 16.dp)
                    .padding(horizontal = 16.dp),
                thickness = 1.dp,
                color = Gray_4
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star_fill),
                        contentDescription = "",
                        tint = Text2_3,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(doctor.rating.toString(), color = Text2_2, fontSize = 14.sp)
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text("($reviewNumber)", color = Text2_3, fontSize = 12.sp)
                }
                Row {
                    Spacer(modifier = Modifier.width(4.dp))
                    val price = doctor.price
                    Text(
                        "Konsultatsiya narxi: ${price.toString().chunked(3).joinToString(separator = " ")} so'm",
                        color = Text2_2,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

