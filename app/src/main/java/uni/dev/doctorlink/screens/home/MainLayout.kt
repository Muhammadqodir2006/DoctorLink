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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
fun MainLayout(vm: HomeViewModel) {
    val topDoctors = vm.topDoctors.observeAsState().value!!
    val topHospitals = vm.topHospitals.observeAsState().value!!
    val loadingTopDoctors = vm.loadingTopDoctors.observeAsState().value!!
    val loadingTopHospitals = vm.loadingTopHospitals.observeAsState().value!!


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
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { vm.openAllHospitals() }
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

        if (loadingTopHospitals) Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        else Column {
            if (topHospitals.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                ) {
                    Text(text = "Ushbu hududda shifoxonalar topilmadi", modifier = Modifier.align(Alignment.Center))
                }
            } else {
                val len = if (topHospitals.size > 3) 3 else topHospitals.size
                for (i in 0 until len) {
                    val h = topHospitals[i]
                    HospitalItem(vm.user.key!!, h, false, vm.navController)
                }
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
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { vm.openAllDoctors() }
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
        if (loadingTopDoctors) Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        else Column {
            if (topDoctors.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                ) {
                    Text(text = "Ushbu hududda doktorlar topilmadi", modifier = Modifier.align(Alignment.Center))
                }
            } else {
                val len = if (topDoctors.size > 5) 5 else topDoctors.size
                for (i in 0 until len) {
                    val d = topDoctors[i]
                    DoctorItem(d, vm.user.key!!, vm.navController, false)
                }
            }
        }


    }
}