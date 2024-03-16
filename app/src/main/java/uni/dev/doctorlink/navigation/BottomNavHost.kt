package uni.dev.doctorlink.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uni.dev.doctorlink.screens.bookings.BookingsView
import uni.dev.doctorlink.screens.bookings.BookingsViewModel
import uni.dev.doctorlink.screens.saved.SavedView
import uni.dev.doctorlink.screens.home.HomeView
import uni.dev.doctorlink.screens.home.HomeViewModel
import uni.dev.doctorlink.screens.profile.ProfileView
import uni.dev.doctorlink.screens.saved.SavedViewModel

@Composable
fun BottomNavigationHost(bottomNavController: NavHostController, navController: NavHostController){
    NavHost(navController = bottomNavController, startDestination = BottomNavScreen.Home.route , modifier = Modifier.fillMaxSize()){
        composable(route = BottomNavScreen.Home.route){
            val homeViewModel = HomeViewModel(navController, bottomNavController)
            HomeView(homeViewModel)
        }
        composable(route = BottomNavScreen.Saved.route){
            val savedViewModel = SavedViewModel(navController)
            SavedView(savedViewModel)
        }
        composable(route = BottomNavScreen.Bookings.route){
            val bookingsViewModel = BookingsViewModel()
            BookingsView(bookingsViewModel)
        }
        composable(route = BottomNavScreen.Profile.route){
            ProfileView()
        }
    }
}