package uni.dev.doctorlink.navigation

import androidx.compose.runtime.Composable
import uni.dev.doctorlink.screens.splash.SplashView
import uni.dev.doctorlink.screens.splash.SplashViewModel

sealed class Screen(val route:String) {
    object Splash:Screen("splash")
    object Welcome:Screen("welcome")
    object Main:Screen("main")
    object PhoneNumber:Screen("phone_number")
    object SmsCode:Screen("sms_code")

}