package uni.dev.doctorlink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import uni.dev.doctorlink.navigation.NavigationHost
import uni.dev.doctorlink.screens.comment.CommentView
import uni.dev.doctorlink.screens.comment.CommentViewModel
import uni.dev.doctorlink.screens.userDetails.UserDetails
import uni.dev.doctorlink.screens.userDetails.UserDetailsViewModel
import uni.dev.doctorlink.ui.theme.DoctorLinkTheme
import uni.dev.doctorlink.util.Api

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoctorLinkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val navController = rememberNavController()
                    val bottomNavController = rememberNavController()

//                    Api.deleteAllComments()
                    NavigationHost(navController, this, context, bottomNavController)
//                    AppointmentView(vm = AppointmentViewModel(navController, context, "-NtI-9RoUP-O6Wc67VCp"))
//                    MainView(rememberNavController())
//                    WelcomeView(welcomeViewModel = WelcomeViewModel(navController))
//                    DoctorView(navController)
//                    SmsCodeView()
//                    UserDetails(vm = UserDetailsViewModel(navController, "+998908496335", LocalContext.current), navController =navController )
//                    Profi leView(vm = ProfileViewModel(navController, LocalContext.current))
//                    HospitalProfileView(vm = HospitalProfileViewModel(navController, context, "-NtG3_9wJNlRpWT45rSW"))
//                    CommentView(vm = CommentViewModel(navController, context, "-NtMdIDxZILl3cuXnepZ", "-NtI-9RoUP-O6Wc67VCp"))
                }
            }
        }
    }
}