package uni.dev.doctorlink.screens.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Edit
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import uni.dev.doctorlink.R
import uni.dev.doctorlink.component.BirthdayDialog
import uni.dev.doctorlink.component.Dialog
import uni.dev.doctorlink.component.ProgressIndicator
import uni.dev.doctorlink.component.RegionDialog
import uni.dev.doctorlink.ui.theme.Background
import uni.dev.doctorlink.ui.theme.Blue
import uni.dev.doctorlink.ui.theme.Blue_2
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Primary_3

@Preview
@Composable
fun ProfilePrv() {
    ProfileView(ProfileViewModel(rememberNavController(), LocalContext.current))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileView(vm: ProfileViewModel) {
    val name = vm.name.observeAsState().value!!
    val surname = vm.surname.observeAsState().value!!
    val birthYear = vm.birthYear.observeAsState().value!!
    val dialogOpen = vm.dialogOpen.observeAsState().value!!
    val dialogContent = vm.dialogContent.observeAsState().value!!
    val loading = vm.loading.observeAsState().value!!
    val reg = vm.reg.observeAsState().value!!
    val readOnly = vm.readOnly.observeAsState().value!!


    Column(
        Modifier
            .fillMaxSize().background(Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(title = {}, actions = {
            AnimatedVisibility(visible = readOnly) {
                IconButton(onClick = {
                    vm.makeEditable()
                }) {
                    Icon(Icons.Rounded.Edit, contentDescription = "")
                }
            }
            IconButton(onClick = {
                vm.openLogoutDialog()
            }) {
                Icon(Icons.AutoMirrored.Rounded.ExitToApp, contentDescription = "")
            }
        })
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 12.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profile_fill), contentDescription = "", tint = Gray_3, modifier = Modifier
                    .size(120.dp)
                    .border(3.dp, Gray_3, CircleShape)
                    .padding(12.dp)

            )
            Spacer(modifier = Modifier.height(36.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {
                    vm.updateName(it)
                },
                readOnly = readOnly,
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
                readOnly = readOnly,
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
                    vm.openBirthYearDialog()
                },
                enabled = !readOnly,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Gray_3),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(
                    Color.Transparent
                ),
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
                    vm.openRegionDialog()
                },
                enabled = !readOnly,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Gray_3),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(
                    Color.Transparent
                )
            ) {
                Text(
                    if (reg.id == -1) "Hududni tanlang" else reg.name!!,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 12.dp)
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            AnimatedVisibility(visible = !readOnly) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    TextButton(
                        onClick = { vm.openUpdateDialog() },
                        colors = ButtonDefaults.buttonColors(containerColor = Blue, disabledContainerColor = Blue_2),
                        enabled = name != vm.user.name || surname != vm.user.surname || birthYear != vm.user.birthyear || reg.id != vm.user.regionId,
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Text(
                            text = "Saqlash",
                            color = White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(horizontal = 40.dp)
                        )
                    }
                    TextButton(
                        onClick = { vm.makeReadOnly() },
                        colors = ButtonDefaults.buttonColors(containerColor = Gray_3),
                        shape = RoundedCornerShape(12.dp),
                        enabled = name.isNotBlank() && surname.isNotBlank() && birthYear != 0 && reg.id != -1,
                    ) {
                        Text(
                            text = "Bekor qilish",
                            color = Color(0f, 0f, 0f, .3f),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }
                }
            }
        }

    }
    AnimatedVisibility(
        visible = dialogOpen
    ) {
        when (dialogContent) {
            0 -> Dialog(
                text = "Akkauntdan chiqmoqchimisiz?",
                icon = painterResource(id = R.drawable.alert),
                onDismiss = { vm.closeDialog() },
                onConfirm = {
                    vm.logout()
                })

            1 ->
                BirthdayDialog(onClick = { year ->
                    vm.updateBirthYear(year)
                    vm.closeDialog()
                }, onDismiss = { vm.closeDialog() }, birthYear)

            2 -> RegionDialog(onClick = {
                vm.closeDialog()
                vm.updateRegion(it)
            }, onDismiss = { vm.closeDialog() }, region = reg)

            3 -> Dialog(
                text = "Ma'lumotlarni saqlamoqchimisiz?",
                icon = painterResource(id = R.drawable.alert),
                onDismiss = { vm.closeDialog() },
                onConfirm = { vm.updateUser() })
        }
    }
    AnimatedVisibility(visible = loading, modifier = Modifier.fillMaxSize()) {
        ProgressIndicator()
    }
}

