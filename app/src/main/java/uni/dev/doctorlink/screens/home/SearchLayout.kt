package uni.dev.doctorlink.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.items.DoctorItem
import uni.dev.doctorlink.items.HospitalItem
import uni.dev.doctorlink.ui.theme.Text

@Composable
fun SearchLayout(vm: HomeViewModel) {
    val searchedState = vm.searchedState.observeAsState().value!!
    val searchedHospitals = vm.searchedHospitals.observeAsState().value!!
    val searchedDoctors = vm.searchedDoctors.observeAsState().value!!
    val loadingDoc = vm.loadingTopDoctors.observeAsState().value!!
    val loadingHos = vm.loadingTopHospitals.observeAsState().value!!

    Column(
        Modifier.fillMaxWidth()
    ) {
        val searchHistory = vm.searchHistory.observeAsState().value!!
        Spacer(modifier = Modifier.height(6.dp))
        AnimatedVisibility(visible = !searchedState) {
            Column {
                Text(text = "Yaqindagi", fontWeight = FontWeight.W600, modifier = Modifier.padding(start = 12.dp))
                Spacer(modifier = Modifier.height(4.dp))
                LazyColumn {
                    items(searchHistory.size) {
                        Column {
                            Text(text = "  ${searchHistory[it]}", modifier = Modifier
                                .clickable {
                                    vm.search(searchHistory[it])
                                }
                                .clip(RoundedCornerShape(4.dp))
                                .fillMaxWidth()
                                .background(Color(0f, 0f, 0f, .03f))
                                .padding(vertical = 10.dp)
                                .clip(RoundedCornerShape(4.dp)), fontSize = 16.sp, color = Text)
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
        AnimatedVisibility(visible = searchedState) {
            Column(Modifier.fillMaxWidth()) {
                LazyColumn {
                    items(searchedDoctors) {
                        DoctorItem(doctor = it, userKey = vm.user.key!!, navController = vm.navController, showHospitalLocation = true)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn {
                    items(searchedHospitals) {
                        HospitalItem(userKey = vm.user.key!!, hospital = it, showLocation = true, navController = vm.navController)
                    }
                }
            }
        }
    }
    AnimatedVisibility(visible = (searchedDoctors.isEmpty() && searchedHospitals.isEmpty()) && (!loadingDoc && !loadingHos)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Qidiruv natijasi topilmadi", modifier = Modifier.align(Alignment.Center), color = Color.Gray)
        }
    }
    AnimatedVisibility(visible = loadingDoc && loadingHos && searchedState) {
        Box (Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}