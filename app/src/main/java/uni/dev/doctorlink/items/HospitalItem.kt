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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import uni.dev.doctorlink.R
import uni.dev.doctorlink.model.Hospital
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2_3
import uni.dev.doctorlink.ui.theme.Text_2
import uni.dev.doctorlink.util.Api

@Composable
fun HospitalItem(userKey: String, hospital: Hospital, showLocation: Boolean, navController: NavController) {
    val savedIconRes = remember {
        mutableIntStateOf(R.drawable.saved_outline)
    }
    Api.isSaved(userKey, hospital.key!!, false) {
        if (it) savedIconRes.intValue = R.drawable.saved_fill
    }
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = {
            navController.navigate("hospital_profile/${hospital.key}")
        }
    ) {
        Column(Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    hospital.photo!![0],
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.error)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column {
                        Text(
                            text = hospital.name!!,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = Text_2,
                            fontSize = 17.sp
                        )
                        Text(
                            text = hospital.schedule!!,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = Text2_3,
                            fontSize = 12.sp
                        )
                    }
                    if (showLocation) Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.LocationOn,
                            contentDescription = "",
                            tint = Primary,
                            modifier = Modifier.size(14.dp)
                        )
                        val location = Api.getRegion(hospital.regionId!!).name!!
                        Text(
                            text = location,
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                            fontSize = 13.sp
                        )

                    }

                }

                IconButton(onClick = {
                    Api.savedHospitalUpdate(userKey, hospital.key!!) {
                        savedIconRes.intValue = if (it) R.drawable.saved_fill else R.drawable.saved_outline
                    }
                }) {
                    Icon(
                        painterResource(savedIconRes.intValue),
                        contentDescription = "",
                        tint = Gray_2
                    )
                }
            }

        }
    }
}
