package uni.dev.doctorlink.navigation

sealed class Screen(val route:String) {
    object Splash:Screen("splash")
    object Welcome:Screen("welcome")
    object Main:Screen("main")
    object PhoneNumber:Screen("telegram_user")
    object SmsCode:Screen("sms_code" + "/{phone}" + "/{verId}")
    object Notifications:Screen("notifications")
    object UserDetails:Screen("user_details" + "/{phone}")
    object DoctorProfile:Screen("doctor_profile" + "/{doctor_key}")
    object Appointment:Screen("appointment" + "/{doctor_key}")
    object Comment:Screen("comment" + "/{booking_key}" + "/{doctor_key}")
    object HospitalProfile:Screen("hospital_profile" + "/{hospital_key}")

}