package uni.dev.doctorlink.screens.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsView(navController: NavController) {
    val notificationsEmpty = true
    Column(Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text(text = "Bildirishnomalar", fontSize = 18.sp) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = "")
                }
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(scrolledContainerColor = Color.White, containerColor = Color.White))
        LazyColumn {

        }
    }
    if (notificationsEmpty)
        Box(Modifier.fillMaxSize()) {
            Text(
                text = "Bildirishnomalar yo'q",
                modifier = Modifier.align(Alignment.Center),
                color = Color.Gray
            )
        }
}