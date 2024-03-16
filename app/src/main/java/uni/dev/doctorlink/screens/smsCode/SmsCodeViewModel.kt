package uni.dev.doctorlink.screens.smsCode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import uni.dev.doctorlink.navigation.Screen

class SmsCodeViewModel(val navController: NavHostController) {
    val codeLength = 5
    val username = "Falonchi"

    private val _code = MutableLiveData("")
    val code: LiveData<String> = _code

    private val _codeError = MutableLiveData(false)
    val codeError: LiveData<Boolean> = _codeError

    private val _dialogOpen = MutableLiveData(false)
    val dialogOpen: LiveData<Boolean> = _dialogOpen


    fun updateCode(new: String) {
        _code.value = new
        _codeError.value = false
        if (_code.value!!.length == codeLength) {
            checkCode()
        }
    }

    private fun checkCode() {
        //  TODO

//        _codeError.value = true
//        _code.value = ""


        navController.navigate(Screen.UserDetails.route)
    }

    fun openDialog() {
        _dialogOpen.value = true
    }

    fun closeDialog() {
        _dialogOpen.value = false
    }

    fun updateCodeError() {
        _codeError.value = false
    }
}
