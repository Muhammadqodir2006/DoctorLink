package uni.dev.doctorlink.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import uni.dev.doctorlink.navigation.BottomNavigationHost
import uni.dev.doctorlink.screens.home.HomeViewModel

@Composable
fun MainView(navController: NavHostController, bottomNavController: NavHostController) {
    val homeViewModel = HomeViewModel(navController, bottomNavController, LocalContext.current)

    Column {
        Box(modifier = Modifier.weight(1f)) { BottomNavigationHost(bottomNavController, navController, homeViewModel) }
        BottomNavBar(bottomNavController = bottomNavController)
    }
}