package uni.dev.doctorlink.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uni.dev.doctorlink.screens.main.MainView
import uni.dev.doctorlink.screens.notifications.NotificationsView
import uni.dev.doctorlink.screens.smsCode.SmsCodeView
import uni.dev.doctorlink.screens.smsCode.SmsCodeViewModel
import uni.dev.doctorlink.screens.splash.SplashView
import uni.dev.doctorlink.screens.splash.SplashViewModel
import uni.dev.doctorlink.screens.telegramUser.TelegramUserView
import uni.dev.doctorlink.screens.telegramUser.TelegramUserViewModel
import uni.dev.doctorlink.screens.userDetails.UserDetails
import uni.dev.doctorlink.screens.userDetails.UserDetailsViewModel
import uni.dev.doctorlink.screens.welcome.WelcomeView
import uni.dev.doctorlink.screens.welcome.WelcomeViewModel

@Composable
fun NavigationHost(navController: NavHostController) {
    val timeNorm = 300
    val context = LocalContext.current
    NavHost(
        navController,
        startDestination = Screen.Main.route,
        modifier = Modifier.background(Color.White)
    ) {
        composable(route = Screen.Splash.route, enterTransition = {
            fadeIn()
        }) {
            val splashViewModel = SplashViewModel(navController, context)
            SplashView(splashViewModel = splashViewModel)
        }
        composable(route = Screen.Welcome.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }) {
            val welcomeViewModel = WelcomeViewModel(navController)
            WelcomeView(welcomeViewModel)
        }
        composable(route = Screen.TelegramUser.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }, popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm))
        }) {
            val telegramUserViewModel = TelegramUserViewModel(navController, context)
            TelegramUserView(telegramUserViewModel)
        }
        composable(route = Screen.SmsCode.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }, exitTransition = {
            fadeOut(tween(timeNorm))
        }, popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(timeNorm)
            )
        }) {
            val smsCodeViewModel = SmsCodeViewModel(navController)
            SmsCodeView(navController, smsCodeViewModel)
        }
        composable(route = Screen.Notifications.route) {
            NotificationsView(navController)
        }
        composable(route = Screen.UserDetails.route, popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(timeNorm)
            )
        }, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }, exitTransition = {
            fadeOut(tween(timeNorm))
        }) {
            val userDetailsViewModel = UserDetailsViewModel(navController)
            UserDetails(userDetailsViewModel, navController)
        }
        composable(Screen.Main.route, enterTransition = {
            scaleIn(animationSpec = tween(timeNorm), 1.1f)
        }) {
            MainView(navController)
        }

    }
}