package uni.dev.doctorlink.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.items.DoctorItem
import uni.dev.doctorlink.items.HospitalItem
import uni.dev.doctorlink.ui.theme.Blue

@Composable
fun MainLayout(vm: HomeViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shifoxonalar",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Box(
                Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .clickable { }
            ) {
                Text(
                    text = "Hammasi",
                    color = Blue,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Column {
            for (i in 1..3) {
                HospitalItem()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Doktorlar",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Box(
                Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .clickable { }
            ) {
                Text(
                    text = "Hammasi",
                    color = Blue,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Column {
            for (i in 1..5) {
                DoctorItem()
            }
        }
    }
}