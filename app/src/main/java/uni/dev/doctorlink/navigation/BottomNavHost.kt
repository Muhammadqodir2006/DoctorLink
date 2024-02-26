package uni.dev.doctorlink.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uni.dev.doctorlink.screens.bookings.BookingsView
import uni.dev.doctorlink.screens.saved.SavedView
import uni.dev.doctorlink.screens.home.HomeView
import uni.dev.doctorlink.screens.profile.ProfileView

@Composable
fun BottomNavigationHost(bottomNavController: NavHostController, navController: NavHostController){
    NavHost(navController = bottomNavController, startDestination = BottomNavScreen.Home.route , modifier = Modifier.fillMaxSize()){
        composable(route = BottomNavScreen.Home.route){
            HomeView()
        }
        composable(route = BottomNavScreen.Saved.route){
            SavedView()
        }
        composable(route = BottomNavScreen.Bookings.route){
            BookingsView()
        }
        composable(route = BottomNavScreen.Profile.route){
            ProfileView()
        }
    }
}