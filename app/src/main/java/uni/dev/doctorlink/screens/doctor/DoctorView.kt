package uni.dev.doctorlink.screens.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
//    val name = "Hasan Bo'ronov"
//    val specialty = "Pediatr"
//    val rating = 4.7
//    val reviewNumber = 124
//    val image = painterResource(id = R.drawable.img1)
//    val clinic = "SmileUp Dental"
//    Column(Modifier.fillMaxSize()) {
//        TopAppBar(
//            title = { },
//            navigationIcon = {
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(painterResource(id = R.drawable.arrow_back), contentDescription = "")
//                }
//            },
//        )
//
//        Column(
//            Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    image,
//                    contentDescription = "",
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(RoundedCornerShape(12.dp)),
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.width(12.dp))
//                Column(modifier = Modifier.weight(1f)) {
//                    Text(
//                        text = name,
//                        fontWeight = FontWeight.ExtraBold,
//                        color = Text_2,
//                        fontSize = 18.sp
//                    )
//                    Spacer(modifier = Modifier.height(2.dp))
//                    Text(
//                        text = specialty,
//                        fontWeight = FontWeight.W600,
//                        fontSize = 16.sp,
//                        color = Text2_2,
//                    )
//                }
//
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        painterResource(id = R.drawable.saved_outline),
//                        contentDescription = "",
//                        tint = Gray_2
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(12.dp))
//            TextButton(
//                onClick = { /*TODO*/ }, colors = ButtonDefaults.textButtonColors(
//                    Primary_3
//                ), shape = RoundedCornerShape(12.dp), modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    text = clinic,
//                    fontWeight = FontWeight.W600,
//                    fontSize = 16.sp,
//                    color = Primary,
//                    modifier = Modifier.padding(horizontal = 24.dp)
//                )
//            }
//            TextButton(
//                onClick = { /*TODO*/ }, colors = ButtonDefaults.textButtonColors(
//                    Gray_4
//                ), shape = RoundedCornerShape(12.dp), modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.star_fill),
//                    contentDescription = "",
//                    tint = Text2_3,
//                    modifier = Modifier.size(18.dp)
//                )
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(rating.toString(), color = Text2_2, fontSize = 16.sp)
//                Spacer(modifier = Modifier.width(4.dp))
//                Text("($reviewNumber)", color = Text2_3, fontSize = 13.sp)
//            }
//        }
//    }
    Row(modifier = Modifier.padding(top = 15.dp, start = 15.dp)) {
        Image(
             Icons.Rounded.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
        Spacer(modifier = Modifier.width(180.dp))
        Image(
            Icons.Rounded.Favorite ,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)

        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            Icons.Rounded.LocationOn,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)

        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            Icons.Rounded.Info,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)

        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            Icons.Rounded.Share,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)

        )
    }

    Spacer(modifier = Modifier.width(8.dp))

    Row(modifier = Modifier.padding(top = 50.dp, start = 15.dp)) {
        Image(
            painter = painterResource(R.drawable.img1),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, Primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Hasan",
                color = uni.dev.doctorlink.ui.theme.Text
            )

            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Urolog")
        }
    }

    Spacer(modifier = Modifier.width(8.dp))



    Row(modifier = Modifier.padding(top = 120.dp, start = 15.dp)) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .size(width = 170.dp, height = 80.dp)
        ) {
            Row (modifier = Modifier
                .padding(15.dp)){
                Image(
                    Icons.Rounded.MailOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "4 years",
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "Experience")
                }
            }

        }


        Spacer(modifier = Modifier.width(20.dp))

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .size(width = 170.dp, height = 80.dp)

        ){
            Row(modifier = Modifier
                .padding(15.dp)) {
                Image(
                    Icons.Rounded.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "0.00",
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "Comments")
                }

            }
        }


    }

    Spacer(modifier = Modifier.width(8.dp))

    Row(modifier = Modifier.padding(top = 210.dp, start = 30.dp)) {
        Column {
            Text(
                text = "Work Place",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row {
                Image(
                    Icons.Rounded.LocationOn,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "BM Clinics")
            }

        }
    }

    Row(modifier = Modifier.padding(top = 270.dp, start = 30.dp)) {
        Column {
            Text(
                text = "Work Schedule",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row {
                Image(
                    Icons.Rounded.Home,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(top = 5.dp)

                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday")
            }

        }
    }

    Row(verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .padding(30.dp)) {
        Column {
            Text(text = "Check-up Price")

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "50 000 sum",
                fontWeight = FontWeight.Bold)

        }
        Spacer(modifier = Modifier.width(40.dp))
        Column {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF007F00)
                ),
                modifier = Modifier
                    .size(width = 170.dp, height = 50.dp)

            ){
                Text(text = "Register",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xF1FFFFFF),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                )
            }

        }
    }
}





