package uni.dev.doctorlink.items

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2_2
import uni.dev.doctorlink.ui.theme.Text2_3
import uni.dev.doctorlink.ui.theme.Text_2

@Preview
@Composable
fun DoctorItem() {
    val name = "Hasan Bo'ronov"
    val specialty = "Stomatolog"
    val price = "100000"
    val rating = 4.7
    val reviewNumber = 124
    val image = painterResource(id = R.drawable.img1)
    val clinic = "SmileUp Dental"
    val clinicLocation = "Yashnobod"
    val showClinic = true

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 4.dp),
        onClick = {}
    ) {
        Column(Modifier.padding(vertical = 20.dp, horizontal = 16.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    image,
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = name,
                        fontWeight = FontWeight.W800,
                        color = Text_2,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = specialty,
                        fontWeight = FontWeight.W600,
                        fontSize = 14.sp,
                        color = Text2_2,
                    )
                    if (showClinic) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Row {
                            Text(
                                text = clinic,
                                fontWeight = FontWeight.W800,
                                fontSize = 12.sp,
                                color = Primary,
                            )
                            Text(
                                text = " ($clinicLocation)",
                                fontWeight = FontWeight.W800,
                                fontSize = 12.sp,
                                color = Text2_3,
                            )
                        }
                    }
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(id = R.drawable.saved_outline),
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
                    Text(rating.toString(), color = Text2_2, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("($reviewNumber)", color = Text2_3, fontSize = 12.sp)
                }
                Row {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        if (price.length > 3) "Konsultatsiya: ${
                            price.substring(
                                0,
                                price.length - 3
                            )
                        } ${
                            price.substring(
                                price.length - 3,
                                price.length
                            )
                        } so'm" else price, color = Text2_2, fontSize = 14.sp, fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}