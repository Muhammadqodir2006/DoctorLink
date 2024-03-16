package uni.dev.doctorlink.navigation

sealed class Screen(val route:String) {
    object Splash:Screen("splash")
    object Welcome:Screen("welcome")
    object Main:Screen("main")
    object TelegramUser:Screen("telegram_user")
    object SmsCode:Screen("sms_code")
    object Notifications:Screen("notifications")
    object UserDetails:Screen("user_details")

}