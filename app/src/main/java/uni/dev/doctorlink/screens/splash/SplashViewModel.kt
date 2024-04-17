package uni.dev.doctorlink.screens.splash

import android.content.Context
import androidx.navigation.NavHostController
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.util.SharedHelper

class SplashViewModel(private val navController: NavHostController, private val context: Context) {

    fun navigate() {
        val route = getRoute(context)
        navController.navigate(route) {
            popUpTo(navController.graph.id)
        }
    }
    init {
        if (SharedHelper.getInstance(context).getDeleteUser()) SharedHelper.getInstance(context).setDeleteUser(false)
    }

    private fun getRoute(context: Context): String {
        val sharedHelper = SharedHelper.getInstance(context)
        if (sharedHelper.showWelcome()) return Screen.Welcome.route
        return if (sharedHelper.getUser().key == null)
            Screen.PhoneNumber.route else Screen.Main.route
    }
}