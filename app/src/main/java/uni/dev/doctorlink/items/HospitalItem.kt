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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_2
import uni.dev.doctorlink.ui.theme.Text2_2
import uni.dev.doctorlink.ui.theme.Text2_3
import uni.dev.doctorlink.ui.theme.Text_2

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalItem() {
    val name = "SmileUp Dental"
    val schedule = "08:00-17:00 Du-Sha"
    val image = painterResource(id = R.drawable.img2)
    val location = "Yashnobod"


    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {}
    ) {
        Column(Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    image,
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
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
                            text = name,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = Text_2,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = schedule,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = Text2_3,
                            fontSize = 12.sp
                        )
                    }
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Icon(
                            Icons.Rounded.LocationOn,
                            contentDescription = "",
                            tint = Primary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = location,
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                            fontSize = 13.sp
                        )
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

        }
    }
}
