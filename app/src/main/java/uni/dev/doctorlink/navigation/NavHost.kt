package uni.dev.doctorlink.navigation

import android.app.Activity
import android.content.Context
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uni.dev.doctorlink.screens.appointment.AppointmentView
import uni.dev.doctorlink.screens.appointment.AppointmentViewModel
import uni.dev.doctorlink.screens.comment.CommentView
import uni.dev.doctorlink.screens.comment.CommentViewModel
import uni.dev.doctorlink.screens.doctor.DoctorProfileView
import uni.dev.doctorlink.screens.doctor.DoctorProfileViewModel
import uni.dev.doctorlink.screens.hospital.HospitalProfileView
import uni.dev.doctorlink.screens.hospital.HospitalProfileViewModel
import uni.dev.doctorlink.screens.main.MainView
import uni.dev.doctorlink.screens.notifications.NotificationsView
import uni.dev.doctorlink.screens.notifications.NotificationsViewModel
import uni.dev.doctorlink.screens.phoneNumber.PhoneNumberView
import uni.dev.doctorlink.screens.phoneNumber.PhoneNumberViewModel
import uni.dev.doctorlink.screens.smsCode.SmsCodeView
import uni.dev.doctorlink.screens.smsCode.SmsCodeViewModel
import uni.dev.doctorlink.screens.splash.SplashView
import uni.dev.doctorlink.screens.splash.SplashViewModel
import uni.dev.doctorlink.screens.userDetails.UserDetails
import uni.dev.doctorlink.screens.userDetails.UserDetailsViewModel
import uni.dev.doctorlink.screens.welcome.WelcomeView
import uni.dev.doctorlink.screens.welcome.WelcomeViewModel

@Composable
fun NavigationHost(
    navController: NavHostController, activity: Activity, context: Context, bottomNavController: NavHostController
) {
    val timeNorm = 300
    NavHost(
        navController,
        startDestination = Screen.Splash.route,
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
        composable(route = Screen.DoctorProfile.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm)
            )
        }, popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm)
            )
        }, arguments = listOf(navArgument("doctor_key") { type = NavType.StringType })) {
            val doctorKey = it.arguments?.getString("doctor_key")!!
            val doctorProfileViewModel = DoctorProfileViewModel(navController, doctorKey, context)
            DoctorProfileView(vm = doctorProfileViewModel)
        }
        composable(
            route = Screen.Comment.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm)
                )
            },
            arguments = listOf(navArgument("booking_key") { type = NavType.StringType }, navArgument("doctor_key") { type = NavType.StringType }),
        ) {
            val bookingKey = it.arguments?.getString("booking_key")!!
            val doctorKey = it.arguments?.getString("doctor_key")!!
            val commentViewModel = CommentViewModel(navController, context, bookingKey, doctorKey)
            CommentView(vm = commentViewModel)
        }
        composable(route = Screen.Appointment.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm)
            )
        }, arguments = listOf(navArgument("doctor_key") { type = NavType.StringType })) {
            val doctorKey = it.arguments?.getString("doctor_key")!!
            val appointmentViewModel = AppointmentViewModel(navController, context, doctorKey)
            AppointmentView(vm = appointmentViewModel)
        }
        composable(route = Screen.HospitalProfile.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm)
            )
        }, arguments = listOf(navArgument("hospital_key") { type = NavType.StringType })) {
            val hospitalKey = it.arguments?.getString("hospital_key")!!
            val hospProfileViewModel = HospitalProfileViewModel(navController, context, hospitalKey)
            HospitalProfileView(vm = hospProfileViewModel)
        }
        composable(route = Screen.Notifications.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm)
            )
        }) {
            val notificationsViewModel = NotificationsViewModel(navController, context)
            NotificationsView(notificationsViewModel)
        }
        composable(route = Screen.PhoneNumber.route, enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm))
        }, popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm))
        }) {
            val phoneNumberViewModel = PhoneNumberViewModel(navController, context, activity)
            PhoneNumberView(phoneNumberViewModel)
        }
        composable(route = Screen.SmsCode.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm)
            )
        }, exitTransition = {
            fadeOut(tween(timeNorm))
        }, popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm)
            )
        }, arguments = listOf(navArgument("phone") { type = NavType.StringType }, navArgument("verId") { type = NavType.StringType })
        ) { entry ->
            val phone = entry.arguments?.getString("phone")!!
            val verId = entry.arguments?.getString("verId")!!
            val smsCodeViewModel = SmsCodeViewModel(navController, verId, phone, context)
            SmsCodeView(navController, smsCodeViewModel)
        }

        composable(route = Screen.UserDetails.route, popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(timeNorm)
            )
        }, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(timeNorm)
            )
        }, exitTransition = {
            fadeOut(tween(timeNorm))
        }, arguments = listOf(navArgument("phone") { type = NavType.StringType })) { entry ->
            val phone = entry.arguments?.getString("phone")!!
            val userDetailsViewModel = UserDetailsViewModel(navController, phone, context)
//            val vm = UserDetailsViewModel(navController, "+998908496335", context)
            UserDetails(userDetailsViewModel, navController)
        }
        composable(Screen.Main.route, enterTransition = {
            scaleIn(animationSpec = tween(timeNorm), 1.1f)
        }) {
            MainView(navController, bottomNavController)
        }

    }
}