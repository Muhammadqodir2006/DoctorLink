package uni.dev.doctorlink.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uni.dev.doctorlink.items.DoctorItem
import uni.dev.doctorlink.items.HospitalItem

@Composable
fun SecondLayout(vm: HomeViewModel) {
    val showDoctors = vm.secondLayoutShowDoctors.observeAsState().value!!
    val doctors = vm.topDoctors.observeAsState().value!!
    val hospitals = vm.topHospitals.observeAsState().value!!
    val regionFilterId = vm.topDoctors.observeAsState().value!!
    val showLoc = vm.secondLayShowLoc.observeAsState().value!!
    val loadingDoctor = vm.loadingTopDoctors.observeAsState().value!!
    val loadingHospital = vm.loadingTopHospitals.observeAsState().value!!


    AnimatedVisibility(visible = showDoctors) {
        LazyColumn(Modifier.padding(horizontal = 12.dp)) {
            items(doctors.size) {
                val doctor = doctors[it]
                DoctorItem(doctor = doctor, userKey = vm.user.key!!, navController = vm.navController, showHospitalLocation = showLoc)
            }
        }
    }
    AnimatedVisibility(visible = !showDoctors) {
        LazyColumn(Modifier.padding(horizontal = 12.dp)) {
            items(hospitals.size) {
                val hospital = hospitals[it]
                HospitalItem(userKey = vm.user.key!!, hospital = hospital, showLocation = showLoc, navController = vm.navController)
            }
        }
    }
    AnimatedVisibility(visible = loadingHospital && loadingDoctor) {
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}