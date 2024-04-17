package uni.dev.doctorlink.screens.phoneNumber

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class PhoneNumberViewModel(
    val navController: NavController,
    private val context: Context,
    private val activity: Activity
){
    private val _phone = MutableLiveData("")
    val phone: LiveData<String> = _phone

    private val _phoneError = MutableLiveData(false)
    val usernameError: LiveData<Boolean> = _phoneError

    private val _loading = MutableLiveData(false)
    val loading :LiveData<Boolean> = _loading

    init {
//        SharedHelper.getInstance(context).logOut()
    }

    init {
//        SharedHelper.getInstance(context).logOut()
    }
    fun updatePhone(new: String) {
        if (new.length < 10) {
            _phone.value = new
        }
        if (_phoneError.value!!) _phoneError.value = false
    }

    fun sendCode() {
        if (_phone.value!!.length != 9) {
            _phoneError.value = true
            return
        }
        if (!Api.hasInternet(context)){
            Toast.makeText(context, "Internet aloqasi mavjud emas", Toast.LENGTH_SHORT).show()
            _loading.value = false
            return
        }
        _loading.value = true
        Api.sendCode("+998" + phone.value!!, context, onFail = {
            Toast.makeText(context, "Xatolik yuz berdi. Raqamni tekshirib kodni qaytadan jo'nating", Toast.LENGTH_SHORT).show()
            _loading.value = false
        }) { verificationId ->
            _loading.value = false
            navController.navigate("sms_code/${_phone.value}/$verificationId")
        }
    }
}