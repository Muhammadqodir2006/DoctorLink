package uni.dev.doctorlink.screens.saved

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.items.DoctorItem
import uni.dev.doctorlink.items.HospitalItem
import uni.dev.doctorlink.ui.theme.Blue
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Text_2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedView(vm: SavedViewModel) {
    val savedDoctors = vm.savedDoctors.observeAsState().value!!
    val savedHospitals = vm.savedHospitals.observeAsState().value!!

    val doctorsOpen = vm.doctorsOpen.observeAsState().value!!
    val doctorsLoading = vm.doctorsLoading.observeAsState().value!!
    val hospitalsLoading = vm.hospitalsLoading.observeAsState().value!!


    Column(Modifier.background(Color.Unspecified)) {
        Spacer(modifier = Modifier.height(6.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Card(
                onClick = { vm.openDoctors() },
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
                modifier = Modifier.width(150.dp),
                colors = CardDefaults.cardColors(if (doctorsOpen) Blue else Gray_2)
            ) {
                Text(
                    text = "Doktorlar",
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.W600,
                    color = Color.White
                )
            }
            Spacer(
                modifier = Modifier
                    .width(0.5.dp)
                    .background(Color.White)
            )
            Card(
                onClick = { vm.openHospitals() },
                shape = RoundedCornerShape(bottomEnd = 12.dp, topEnd = 12.dp),
                modifier = Modifier.width(150.dp),
                colors = CardDefaults.cardColors(if (!doctorsOpen) Blue else Gray_2)
            ) {
                Text(
                    text = "Shifoxonalar",
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.W600,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        if (doctorsOpen) {
            if (savedDoctors.isNotEmpty()) LazyColumn(modifier = Modifier.padding(horizontal = 12.dp)) {
                items(savedDoctors.size) {
                    DoctorItem(savedDoctors[it], vm.user.key!!, vm.navController, true)
                }
            } else {
                Column(
                    Modifier
                        .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (doctorsLoading) CircularProgressIndicator()
                    else Text(text = "Saqlangan doktorlar yo'q", color = Gray)
                }

            }
        }
        if (!doctorsOpen) {
            if (savedHospitals.isNotEmpty()) LazyColumn(modifier = Modifier.padding(horizontal = 12.dp)) {
                items(savedHospitals.size) {
                    HospitalItem(vm.user.key!!, savedHospitals[it], true, vm.navController)
                }
            } else {
                Column(
                    Modifier
                        .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (hospitalsLoading) CircularProgressIndicator()
                    else Text(text = "Saqlangan shifoxonalar yo'q")
                }
            }
        }
    }
}