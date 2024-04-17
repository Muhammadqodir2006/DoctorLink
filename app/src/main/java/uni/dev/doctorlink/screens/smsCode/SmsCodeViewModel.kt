package uni.dev.doctorlink.screens.smsCode

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class SmsCodeViewModel(
    val navController: NavHostController,
    val verificationId: String,
    val phone: String,
    val context: Context
) {
    val codeLength = 6

    private val _sec = MutableLiveData(59)

    private val _secString = MutableLiveData("59")
    val secString: LiveData<String> = _secString

    private val _min = MutableLiveData(1)
    val min: LiveData<Int> = _min

    private val _code = MutableLiveData("")
    val code: LiveData<String> = _code

    private val _codeError = MutableLiveData(false)
    val codeError: LiveData<Boolean> = _codeError

    private val _dialogOpen = MutableLiveData(false)
    val dialogOpen: LiveData<Boolean> = _dialogOpen

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun updateCode(new: String) {
        _code.value = new
        _codeError.value = false
        if (_code.value!!.length == codeLength) {
            checkCode()
        }
    }

    fun timeMinus() {
        if (_sec.value!! > 0) {
            _sec.value = _sec.value!! - 1
        } else {
            if (_min.value!! > 0) {
                _min.value = _min.value!! - 1
                _sec.value = 59
            } else {
                Toast.makeText(context, "Tasdiqlash kodi eskirdi", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }
        _secString.value = if (_sec.value!! < 10) "0${_sec.value!!}" else _sec.toString()
        Log.d("TAG", "${_min.value}:${_sec.value}")
    }

    private fun checkCode() {
        if (!Api.hasInternet(context)) {
            Toast.makeText(context, "Internet aloqasi mavjud emas", Toast.LENGTH_SHORT).show()
            _code.value = _code.value!!.dropLast(1)
            return
        }
        _loading.value = true
        Api.checkCode(verificationId = verificationId, smsCode = _code.value!!) {
            if (it) navigate()
            else {
                _loading.value = false
                _codeError.value = true
                _code.value = ""
            }
        }
    }

    private fun navigate() {
        _loading.value = true
        Api.getUser("+998$phone") { user ->
            if (user == null) {
                _loading.value = false
                navController.navigate("user_details/$phone")
            } else {
                _loading.value = false
                SharedHelper.getInstance(context).login(user)
                navController.navigate(Screen.Main.route){
                    popUpTo(navController.graph.id){
                        inclusive = true
                    }
                }
            }
        }
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
