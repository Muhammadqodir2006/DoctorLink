package uni.dev.doctorlink.screens.userDetails

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uni.dev.doctorlink.component.ExitDialog
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.ui.theme.Blue
import uni.dev.doctorlink.ui.theme.Gray
import uni.dev.doctorlink.ui.theme.Gray_3
import uni.dev.doctorlink.ui.theme.Gray_4
import uni.dev.doctorlink.ui.theme.Primary
import uni.dev.doctorlink.ui.theme.Primary_3
import java.time.LocalDate

@Composable
fun UserDetails(vm: UserDetailsViewModel, navController: NavController) {
    val name = vm.name.observeAsState().value!!
    val surname = vm.surname.observeAsState().value!!
    val gender = vm.gender.observeAsState().value!!
    val birthYear = vm.birthYear.observeAsState().value!!
    val dialogOpen = vm.dialogOpen.observeAsState().value!!
    val dialogContent = vm.dialogContent.observeAsState().value!!

    BackHandler {
        vm.updateDialogContent(0)
        vm.closeDialog()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
        ) {
            IconButton(onClick = {
                vm.updateDialogContent(0)
                vm.openDialog()
            }) {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
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
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            RadioButton(
                selected = gender,
                onClick = { vm.updateGender(true) },
                colors = androidx.compose.material3.RadioButtonDefaults.colors(selectedColor = Blue)
            )
            Text(text = "Erkak")
            Spacer(modifier = Modifier.width(24.dp))
            RadioButton(
                selected = !gender, onClick = { vm.updateGender(false) },
                colors = androidx.compose.material3.RadioButtonDefaults.colors(selectedColor = Blue)
            )
            Text(text = "Ayol")
        }
        Spacer(modifier = Modifier.height(16.dp))
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
        Spacer(modifier = Modifier.height(42.dp))
        TextButton(
            onClick = { vm.onContinue() },
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(12.dp),
            enabled = name.isNotBlank() && surname.isNotBlank() && birthYear != 0,
        ) {
            Text(
                text = "Davom etish",
                color = Color.White,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

    }
    AnimatedVisibility(
        visible = dialogOpen
    ) {
        when (dialogContent) {
            0 -> ExitDialog(
                onDismiss = { vm.closeDialog() },
                onConfirm = {
                    navController.popBackStack(
                        route = Screen.TelegramUser.route,
                        inclusive = false
                    )
                })

            1 -> AlertDialog(
                onDismissRequest = { vm.closeDialog() },
                containerColor = Color.White,
                confirmButton = {},
                text = {
                    BirthdayDialogContent(vm, birthYear)
                }
            )
        }
    }
}

@Composable
fun BirthdayDialogContent(vm: UserDetailsViewModel, birthYear: Int) {
    val currentYear = LocalDate.now().year - 18
    LazyColumn(Modifier.fillMaxWidth()) {
        items(80) {
            val year = currentYear - it
            Column(
                modifier = Modifier
                    .background(
                        color = if (birthYear == year) Gray_4 else Color.Transparent,
                        RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        vm.closeDialog()
                        vm.updateBirtYear(year)
                    }
            ) {
                Text(
                    text = (year).toString() + "-yil",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
                HorizontalDivider(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    thickness = 1.dp,
                    color = Gray_4
                )
            }
        }
    }

}
