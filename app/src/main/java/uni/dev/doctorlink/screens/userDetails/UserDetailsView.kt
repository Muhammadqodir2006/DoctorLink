package uni.dev.doctorlink.screens.userDetails

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uni.dev.doctorlink.R
import uni.dev.doctorlink.component.BirthdayDialog
import uni.dev.doctorlink.component.Dialog
import uni.dev.doctorlink.component.ProgressIndicator
import uni.dev.doctorlink.component.RegionDialog
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_3

//@Preview
//@Composable
//fun prv() {
//    UserDetails(
//        vm = UserDetailsViewModel(rememberNavController(), "", LocalContext.current), navController = rememberNavController()
//    )
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetails(vm: UserDetailsViewModel, navController: NavController) {
    val name = vm.name.observeAsState().value!!
    val surname = vm.surname.observeAsState().value!!
    val birthYear = vm.birthYear.observeAsState().value!!
    val dialogOpen = vm.dialogOpen.observeAsState().value!!
    val dialogContent = vm.dialogContent.observeAsState().value!!
    val loading = vm.loading.observeAsState().value!!
    val region = vm.region.observeAsState().value!!

    BackHandler {
        vm.updateDialogContent(0)
        vm.openDialog()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(title = { }, navigationIcon = {
            IconButton(onClick = {
                vm.updateDialogContent(0)
                vm.openDialog()
            }) {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "")
            }
        })
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 12.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ro'yhatdan o'tish",
                fontSize = 28.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.height(36.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {
                    vm.updateName(it)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Gray_3,
                    focusedBorderColor = Primary_3
                ),
                placeholder = { Text(text = "Ism", color = Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = surname,
                onValueChange = {
                    vm.updateSurname(it)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Gray_3,
                    focusedBorderColor = Primary_3
                ),
                placeholder = { Text(text = "Familiya", color = Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    vm.updateDialogContent(1)
                    vm.openDialog()
                },
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Gray_3),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(
                    Color.Transparent
                )
            ) {
                Text(
                    if (birthYear == 0) "Tug'ilgan yil" else "$birthYear-yil",
                    fontSize = 18.sp,
                    color = if (birthYear == 0) Gray else Color.Black,
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 12.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    vm.updateDialogContent(2)
                    vm.openDialog()
                },
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Gray_3),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(
                    Color.Transparent
                )
            ) {
                Text(
                    if (region.id == -1) "Hududni tanlang" else region.name!!,
                    fontSize = 18.sp,
                    color = if (region.id == -1) Gray else Color.Black,
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 12.dp)
                )
            }
            Spacer(modifier = Modifier.height(42.dp))
            TextButton(
                onClick = { vm.onContinue() },
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp),
                enabled = name.isNotBlank() && surname.isNotBlank() && birthYear != 0 && region.id != -1,
            ) {
                Text(
                    text = "Davom etish",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
            Spacer(modifier = Modifier.height(42.dp))
        }

    }
    AnimatedVisibility(
        visible = dialogOpen
    ) {
        when (dialogContent) {
            0 -> Dialog(
                text = "Chqimoqchimisz?",
                icon = painterResource(id = R.drawable.alert),
                onDismiss = { vm.closeDialog() },
                onConfirm = {
                    navController.popBackStack(
                        route = Screen.PhoneNumber.route,
                        inclusive = false
                    )
                })

            1 -> AlertDialog(
                onDismissRequest = { vm.closeDialog() },
                containerColor = Color.White,
                confirmButton = {},
                text = {
                    BirthdayDialog(onClick = { year ->
                        vm.closeDialog()
                        vm.updateBirthYear(year)
                    }, onDismiss = { vm.closeDialog() }, birthYear)
                }
            )

            2 -> RegionDialog(onClick = { reg ->
                vm.closeDialog()
                vm.updateRegion(reg)
            }, onDismiss = { vm.closeDialog() }, region)
        }
    }
    AnimatedVisibility(visible = loading) {
        ProgressIndicator()
    }
}