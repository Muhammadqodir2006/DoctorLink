package uni.dev.doctorlink.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uni.dev.doctorlink.navigation.BottomNavigationHost

@Composable
fun MainView(navController: NavHostController) {
    val bottomNavController = rememberNavController()
    Column {
        Box(modifier = Modifier.weight(1f)) { BottomNavigationHost(bottomNavController, navController) }
        BottomNavBar(bottomNavController = bottomNavController)
    }
}