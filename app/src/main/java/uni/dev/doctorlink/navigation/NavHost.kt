package uni.dev.doctorlink.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uni.dev.doctorlink.screens.main.MainView
import uni.dev.doctorlink.screens.phoneNumber.TelegramUserView
import uni.dev.doctorlink.screens.smsCode.SmsCodeView
import uni.dev.doctorlink.screens.splash.SplashView
import uni.dev.doctorlink.screens.splash.SplashViewModel
import uni.dev.doctorlink.screens.welcome.WelcomeView
import uni.dev.doctorlink.screens.welcome.WelcomeViewModel

@Composable
fun NavigationHost(navController: NavHostController) {
    val timeNorm = 300
    NavHost(navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            val splashViewModel = SplashViewModel(navController)
            SplashView(splashViewModel = splashViewModel)
        }
        composable(route = Screen.Welcome.route) {
            val welcomeViewModel = WelcomeViewModel(navController)
            WelcomeView(welcomeViewModel)
        }
        composable(route = Screen.TelegramUser.route) {
            TelegramUserView()
        }
        composable(route = Screen.SmsCode.route) {
            SmsCodeView()
        }
        composable(
            Screen.Main.route,
            enterTransition = {
                scaleIn(animationSpec = tween(timeNorm), 1.1f)
            }
        ) {
            MainView(navController)
        }

    }
}