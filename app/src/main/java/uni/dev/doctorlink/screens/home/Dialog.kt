package uni.dev.doctorlink.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uni.dev.doctorlink.ui.theme.Gray_4

@Composable
fun HomeDialog(vm: HomeViewModel){
    val dialogContent = vm.dialogContent.observeAsState().value!!
    AlertDialog(
        onDismissRequest = { vm.closeDialog() },
        confirmButton = { },
        text = {
            when (dialogContent) {
                0 -> LocationDialogContent(vm)
            }
        }, containerColor = Color.White
    )
}


@Composable
fun LocationDialogContent(vm: HomeViewModel) {
    val location = vm.location.observeAsState().value!!
    LazyColumn {
        val locations = listOf(
            "Toshkent shahar",
            "Qoraqalpog'iston Respublikasi",
            "Toshkent viloyati",
            "Farg'ona viloyati",
            "Andijon viloyati",
            "Namangan viloyati",
            "Sirdaryo viloyati",
            "Xorazm viloyati",
            "Jizzax viloyati",
            "Samarqand viloyati",
            "Buxoro viloyati",
            "Surxondaryo viloyati",
            "Qashqadaryo viloyati",
            "Navoiy viloyati"
        )
        items(locations) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        vm.changeLocation(it)
                        vm.closeDialog()
                    }
                    .background(if (it == location) Gray_4 else Color.Transparent)
            ) {
                Text(
                    text = it,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                HorizontalDivider(Modifier.fillMaxWidth(), 1.dp, Gray_4)
            }
        }
    }
}
