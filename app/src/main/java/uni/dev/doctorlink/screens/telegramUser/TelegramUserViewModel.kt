package uni.dev.doctorlink.screens.telegramUser

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.navigation.Screen

class TelegramUserViewModel(val navController: NavController, private val context: Context){
    private val  botUrl = "doctorlink_bot"


    private val errorText1 = "Username 5-32 uzunlikdagi raqam va lotin harflaridan iborat bo'lishi kerak"
    private val errorText2 = "Username faqat harflar va '_' bilan boshlanishi mumkin"
    private val errorText3 = "Usernameni kiriting"

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _usernameError = MutableLiveData(false)
    val usernameError: LiveData<Boolean> = _usernameError

    private val _usernameErrorText = MutableLiveData(errorText1)
    val usernameErrorText: LiveData<String> = _usernameErrorText

    private val _dialogOpen = MutableLiveData(false)
    val dialogOpen: LiveData<Boolean> = _dialogOpen

    fun updateUsername(new: String) {
        if (new.length <= 32) {
            _username.value = new
        }
        if (_usernameError.value!!) _usernameError.value = false
    }

    fun sendCode() {
        if (_username.value!!.isBlank()){
            _usernameError.value = true
            _usernameErrorText.value = errorText3
            return
        }
        if (_username.value!!.length !in 5..32) {
            _usernameError.value = true
            _usernameErrorText.value = errorText1
            return
        }
        if (_username.value!![0].isDigit()) {
            _usernameError.value = true
            _usernameErrorText.value = errorText2
            return
        }
        if (sendVerificationCode()) {
            // TODO
            navController.navigate(Screen.SmsCode.route)
        } else _dialogOpen.value = true
    }

    private fun sendVerificationCode(): Boolean {
        // TODO:
        return true
    }

    fun closeDialog() {
        _dialogOpen.value = false
    }

    fun openTelegram() {
        val tgIntent = Intent(Intent.ACTION_VIEW,
            Uri.parse("https://t.me/$botUrl"))
        tgIntent.setPackage("org.telegram.messenger")
        tgIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(tgIntent)
        }catch (e:Exception){
            val packageName = "org.telegram.messenger"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
            context.startActivity(intent)
        }
    }
}