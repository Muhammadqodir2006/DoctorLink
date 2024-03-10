package uni.dev.doctorlink.screens.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uni.dev.doctorlink.R
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_3
import uni.dev.doctorlink.ui.theme.Text2_2
import uni.dev.doctorlink.ui.theme.Text2_3
import uni.dev.doctorlink.ui.theme.Text_2

@Preview
@Composable
fun prv() {
    DoctorView(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorView(navController: NavController) {
    val name = "Hasan Bo'ronov"
    val specialty = "Pediatr"
    val rating = 4.7
    val reviewNumber = 124
    val image = painterResource(id = R.drawable.img1)
    val clinic = "SmileUp Dental"
    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painterResource(id = R.drawable.arrow_back), contentDescription = "")
                }
            },
        )

        Column(
            Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
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
                        fontWeight = FontWeight.ExtraBold,
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
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.textButtonColors(
                    Primary_3
                ), shape = RoundedCornerShape(12.dp), modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = clinic,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = Primary,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
            TextButton(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.textButtonColors(
                    Gray_4
                ), shape = RoundedCornerShape(12.dp), modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star_fill),
                    contentDescription = "",
                    tint = Text2_3,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(rating.toString(), color = Text2_2, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text("($reviewNumber)", color = Text2_3, fontSize = 13.sp)
            }
        }
    }
}




