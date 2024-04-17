package uni.dev.doctorlink.screens.doctor

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uni.dev.doctorlink.R
import uni.dev.doctorlink.items.CommentItem
import uni.dev.doctorlink.ui.theme.Background
import uni.dev.doctorlink.ui.theme.Blue
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_2
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Text2_2
import uni.dev.doctorlink.ui.theme.Text_2
import uni.dev.doctorlink.util.Api

//@Preview
//@Composable
//fun prv() {
//    DoctorProfileView(navController = rememberNavController())
//}

@Composable
fun DoctorProfileView(vm: DoctorProfileViewModel) {
    val name = vm.name.observeAsState().value!!
    val surname = vm.surname.observeAsState().value!!
    val image = vm.image.observeAsState().value!!
    val rating = vm.rating.observeAsState().value!!
    val price = vm.price.observeAsState().value!!
    val specialties = vm.specialties.observeAsState().value!!
    val info = vm.info.observeAsState().value!!
    val hospital = vm.hospital.observeAsState().value!!
    val loading = vm.loading.observeAsState().value!!
    val loadingComments = vm.loadingComments.observeAsState().value!!
    val experience = vm.experience.observeAsState().value!!
    val comments = vm.comments.observeAsState().value!!

    val savedIconResource = remember {
        mutableIntStateOf(R.drawable.saved_outline)
    }

    Api.isSaved(vm.user.key!!, vm.doctorKey, true) {
        if (it) savedIconResource.intValue = R.drawable.saved_fill
    }

    Column(Modifier.fillMaxSize().background(Color.White)) {
        if (loading) Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                Spacer(modifier = Modifier.height(36.dp))

                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = image, contentDescription = "", modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(10)), contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {

                        Text(
                            text = "$name $surname", fontWeight = FontWeight.Bold, color = Text_2, fontSize = 15.sp
                        )
                        LazyRow {
                            items(specialties.size) {
                                //                            Log.d("TAG", "errorCheck : ${doctor.specialty!!.size} , $it")
                                val specialty = Api.getSpecialty(specialties[it]).name!!
                                Text(
                                    text = specialty,
                                    fontWeight = FontWeight.W600,
                                    fontSize = 15.sp,
                                    color = Text2_2,
                                )
                                if (it != specialties.size - 1) Text(
                                    text = ", ",
                                    fontWeight = FontWeight.W600,
                                    fontSize = 15.sp,
                                    color = Text2_2,
                                )
                            }
                        }
                        Text(
                            text = hospital.name!!,
                            fontWeight = FontWeight.W800,
                            fontSize = 14.sp,
                            color = Primary,
                        )
                        Text(
                            text = "(" + Api.getRegion(hospital.regionId!!).name!! + ")",
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp,
                            color = Text2_2,
                        )
                    }


                    IconButton(onClick = {
                        Api.savedDoctorUpdate(vm.user.key!!, vm.doctorKey) {
                            savedIconResource.intValue = if (it) R.drawable.saved_fill
                            else R.drawable.saved_outline
                        }
                    }) {
                        Icon(
                            painterResource(id = savedIconResource.intValue), contentDescription = "", tint = Gray_2
                        )
                    }
                }


                Spacer(modifier = Modifier.height(20.dp))


                Column {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                        Card(colors = CardDefaults.cardColors(Gray_4), modifier = Modifier.weight(1f)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(vertical = 12.dp, horizontal = 24.dp)
                                    .fillMaxWidth(),
                            ) {
                                Text(text = "Tajriba:", color = Gray)
                                Text(text = "$experience yil", color = Text_2)
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Card(colors = CardDefaults.cardColors(Gray_4), modifier = Modifier.weight(1f)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(vertical = 12.dp, horizontal = 24.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(text = "Reyting:", color = Gray)
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                                    Text(text = ((rating * 10).toInt().toFloat() / 10).toString(), color = Text_2)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.star_fill),
                                        contentDescription = "",
                                        tint = Text2_2,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }
                    Column(Modifier.padding(horizontal = 12.dp)) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Info: ", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = info,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = Text2_2,
                            modifier = Modifier.padding(start = 0.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(4.dp))
                    if (loadingComments) Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    else if (comments.isEmpty()) Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        Text(text = "Izohlar yo'q", fontSize = 14.sp, color = Gray, modifier = Modifier.align(Alignment.Center))
                    } else Column {
                        for (comment in comments) {
                            CommentItem(comment = comment)
                        }
                    }
                }

            }
        }
        Row(Modifier.background(Color(0f,0f,0f,.02f)), verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Ko'rik narxi:", fontSize = 13.sp, color = Gray)
                Text(
                    text = "${price.toString().chunked(3).joinToString(separator = " ")} so'm", fontWeight = FontWeight.Bold,
                    color = Blue,
                    fontSize = 15.sp
                )
            }
            Column(Modifier.weight(1f)) {
                TextButton(
                    onClick = { vm.register() },
                    Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(containerColor = Primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Ko'rikka yozilish", color = Color.White)
                }
            }
        }

    }
    Box(Modifier.fillMaxSize()) {
        IconButton(
            onClick = { vm.backButtonClick() },
            colors = IconButtonDefaults.iconButtonColors(Color(1f, 1f, 1f, .7f)),
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "")
        }
    }

}





