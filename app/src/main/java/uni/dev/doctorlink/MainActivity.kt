package uni.dev.doctorlink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import uni.dev.doctorlink.items.DoctorItem
import uni.dev.doctorlink.items.ShowDIs
import uni.dev.doctorlink.navigation.NavigationHost
import uni.dev.doctorlink.screens.doctor.DoctorView
import uni.dev.doctorlink.screens.main.MainView
import uni.dev.doctorlink.screens.smsCode.SmsCodeView
import uni.dev.doctorlink.screens.welcome.WelcomeView
import uni.dev.doctorlink.screens.welcome.WelcomeViewModel
import uni.dev.doctorlink.ui.theme.DoctorLinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoctorLinkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
//                    NavigationHost(navController)
                    MainView(rememberNavController())
//                    WelcomeView(welcomeViewModel = WelcomeViewModel(navController))
//                    DoctorView(navController)
//                    SmsCodeView()
                }
            }
        }
    }
}