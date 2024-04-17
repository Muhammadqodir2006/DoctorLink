package uni.dev.doctorlink.screens.hospital

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import uni.dev.doctorlink.R
import uni.dev.doctorlink.items.DoctorItem
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2_2
import uni.dev.doctorlink.ui.theme.Text2_3
import uni.dev.doctorlink.ui.theme.Text_2
import uni.dev.doctorlink.util.Api


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HospitalProfileView(
    vm: HospitalProfileViewModel
) {
    val name = vm.name.observeAsState().value!!
    val region = vm.region.observeAsState().value!!
    val phones = vm.phones.observeAsState().value!!
    val schedule = vm.schedule.observeAsState().value!!
    val photos = vm.photos.observeAsState().value!!
    val pagerState2 = rememberPagerState()
    val pagerState1 = rememberPagerState()
    val imageOpen = vm.imageOpen.observeAsState().value!!
    val coroutineScope = rememberCoroutineScope()
    val doctors = vm.doctorsOfHospital.observeAsState().value!!
    val loadingDoctors = vm.loadingDoctors.observeAsState().value!!

    val savedIconRes = remember {
        mutableStateOf(R.drawable.saved_outline)
    }
    Api.isSaved(vm.userKey, vm.hospitalKey, false) {
        if (it) savedIconRes.value = R.drawable.saved_fill
    }

    BackHandler {
        coroutineScope.launch {
            pagerState1.scrollToPage(pagerState2.currentPage)
        }
        vm.onBack()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,

        ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .background(
                    color = Color.Gray, shape = RoundedCornerShape(8.dp)
                )
        ) {
            HorizontalPager(
                count = photos.size, modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .background(Color.Black), state = pagerState1, userScrollEnabled = true
            ) {
                val loading = remember {
                    mutableStateOf(true)
                }
                val isError = remember {
                    mutableStateOf(false)
                }
                if (loading.value) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                AsyncImage(
                    model = photos[it],
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            coroutineScope.launch { pagerState2.scrollToPage(pagerState1.currentPage) }
                            vm.openImage()
                        },
                    onError = { error ->
                        Log.d("TAG", "HospitalProfileLoadImage:$error ")
                        isError.value = true
                        loading.value = false
                    },
                    onSuccess = { loading.value = false }
                )
                if (loading.value) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                if (isError.value) Icon(
                    Icons.Rounded.Close,
                    contentDescription = "",
                    tint = Color(0f, 0f, 0f, .8f),
                    modifier = Modifier.size(32.dp)
                )
            }
            Card(
                colors = CardDefaults.cardColors(Color(0f, 0f, 0f, .5f)),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                    for (i in 1..photos.size) TabRowDefaults.Indicator(
                        color = if (pagerState1.currentPage + 1 == i) Color.White else Gray,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(6.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Column {
                    Text(
                        text = name,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = Text_2,
                        fontSize = 17.sp
                    )
                }
//                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.LocationOn,
                        contentDescription = "",
                        tint = Primary,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = region.name!!,
                        fontWeight = FontWeight.Bold,
                        color = Primary,
                        fontSize = 13.sp
                    )

                }

            }

            IconButton(onClick = {
                Api.savedHospitalUpdate(vm.userKey, vm.hospitalKey) {
                    savedIconRes.value = if (it) R.drawable.saved_fill else R.drawable.saved_outline
                }
            }) {
                Icon(
                    painterResource(savedIconRes.value),
                    contentDescription = "",
                    tint = Gray_2
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {


            Spacer(modifier = Modifier.height(12.dp))
            Column(Modifier.padding(horizontal = 16.dp)) {
                Row {
                    Text(text = "Ish jadvali:", fontSize = 16.sp, color = Text_2, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = schedule, fontSize = 14.sp, color = Text2_2, fontWeight = FontWeight.W700)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Text(text = "Aloqa:", fontSize = 16.sp, color = Text_2, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(24.dp))
                    Column {
                        for (phone in phones) {
                            Text(text = phone, fontSize = 14.sp, color = Text2_2, fontWeight = FontWeight.W700)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Shifokorlar ", fontSize = 16.sp, color = Text_2, fontWeight = FontWeight.W700)
                if (loadingDoctors) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    if (doctors.isEmpty()) {
                        Text(text = "Shifoxonada doktorlar topilmadi", modifier = Modifier.padding(top = 12.dp))
                    } else {
                        Column(Modifier.fillMaxWidth()) {
                            for (doctor in doctors) {
                                DoctorItem(doctor = doctor, userKey = vm.userKey, navController = vm.navController, showHospitalLocation = false)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
            }

        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = imageOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HorizontalPager(
                count = photos.size, modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .background(Color.Black), state = pagerState2
            ) {
                val loading = remember {
                    mutableStateOf(true)
                }
                val isError = remember {
                    mutableStateOf(false)
                }
                if (loading.value) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                AsyncImage(
                    model = photos[it],
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    onError = { error ->
                        Log.d("TAG", "HospitalProfileLoadImage:$error ")
                        isError.value = true
                        loading.value = false
                    },
                    onSuccess = { loading.value = false }
                )
                if (loading.value) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                if (isError.value) Icon(Icons.Rounded.Close, contentDescription = "", tint = Color(0f, 0f, 0f, .8f), modifier = Modifier.size(32.dp))
            }
        }

        IconButton(
            onClick = {
                coroutineScope.launch {
                    pagerState1.scrollToPage(pagerState2.currentPage)
                }
                vm.onBack()
            },
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0f, 0f, 0f, .3f)),
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "", tint = Color.White)
        }
    }
}