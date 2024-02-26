package uni.dev.doctorlink.screens.doctor

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Blue_2
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2_2
import uni.dev.doctorlink.ui.theme.Text2_3
import uni.dev.doctorlink.ui.theme.Text_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorView(navController: NavController) {
    val name = "Hasan Bo'ronov"
    val specialty = "Genikolog"
    val rating = 4.7
    val reviewNumber = 124
    val image = painterResource(id = R.drawable.img1)
    val clinic = "SmileUp Dental"
    Column {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painterResource(id = R.drawable.arrow_back), contentDescription = "")
                }
            },
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            Column(Modifier.padding(vertical = 20.dp, horizontal = 16.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {

                        Text(
                            text = name,
                            fontWeight = FontWeight.W800,
                            color = Text_2,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = specialty,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            color = Text2_2,
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.saved_outline),
                            contentDescription = "",
                            tint = Gray_2
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.Home, contentDescription = "", tint = Primary)
                        Text(
                            text = clinic,
                            fontWeight = FontWeight.W400,
                            fontSize = 18.sp,
                            color = Primary,
                        )
                    }
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { }) {
                        Text(
                            text = "Shifoxona profili",
                            color = Blue_2,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .background(
                                    Gray_4
                                )
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.star_fill),
                            contentDescription = "",
                            tint = Text2_3,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(rating.toString(), color = Text2_2, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("($reviewNumber)", color = Text2_3, fontSize = 12.sp)
                    }
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { }) {
                        Text(
                            text = "Sharhlar",
                            color = Blue_2,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .background(
                                    Gray_4
                                )
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}




