package uni.dev.doctorlink.screens.splash

import android.content.Context
import androidx.navigation.NavHostController
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.util.SharedHelper

class SplashViewModel(private val navController: NavHostController) {
    fun navigate(context: Context) {
        val route = getRoute(context)
        navController.navigate(route) {
            popUpTo(navController.graph.id)
        }
    }

    private fun getRoute(context: Context): String {
        val sharedHelper = SharedHelper.getInstance(context)
        if (sharedHelper.showWelcome()){
            return  Screen.Welcome.route
        }
        // TODO: Check user login
        return  Screen.PhoneNumber.route
    }
}