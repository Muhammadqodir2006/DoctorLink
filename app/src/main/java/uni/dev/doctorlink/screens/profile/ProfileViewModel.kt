package uni.dev.doctorlink.screens.profile

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.model.Region
import uni.dev.doctorlink.model.User
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class ProfileViewModel(val navController: NavController, val context: Context) {
    var user = SharedHelper.getInstance(context).getUser()!!

    private var _name = MutableLiveData(user.name!!)
    var name : LiveData<String> =_name

    private var _surname = MutableLiveData(user.surname!!)
    var surname : LiveData<String> =_surname

    private var _birthYear = MutableLiveData(user.birthyear!!)
    var birthYear :LiveData<Int> = _birthYear

    private val _dialogOpen = MutableLiveData(false)
    val dialogOpen : LiveData<Boolean> = _dialogOpen

    private val _dialogContent = MutableLiveData(0)
    val dialogContent :LiveData<Int> = _dialogContent

    private val _region = MutableLiveData(Api.getRegion(user.regionId!!))
    val reg :LiveData<Region> = _region

    private val _loading = MutableLiveData(false)
    val loading :LiveData<Boolean> = _loading

    private val _readOnly = MutableLiveData(true)
    val readOnly:LiveData<Boolean> = _readOnly


    fun updateName(new: String){
        if (new.length <= 35) {
            _name.value = new
        }
    }

    fun closeDialog(){
        _dialogOpen.value = false
    }

    fun openLogoutDialog() {
        _dialogContent.value = 0
        _dialogOpen.value = true
    }
    fun openUpdateDialog() {
        _dialogContent.value = 3
        _dialogOpen.value = true
    }
    fun openBirthYearDialog() {
        _dialogContent.value = 1
        _dialogOpen.value = true
    }
    fun openRegionDialog() {
        _dialogContent.value = 2
        _dialogOpen.value = true
    }

    fun updateSurname(new: String) {
        if (new.length <= 35) {
            _surname.value = new
        }
    }

    fun updateBirthYear(year: Int) {
        _birthYear.value = year
    }

    fun updateRegion(reg: Region) {
        _region.value = reg
    }

    fun makeEditable(){
        _readOnly.value = false
    }
    fun makeReadOnly(){
        _readOnly.value = true
        updateName(user.name!!)
        updateSurname(user.surname!!)
        updateBirthYear(user.birthyear!!)
        updateRegion(Api.getRegion(user.regionId!!))
    }

    fun logout() {
        closeDialog()
        user = User("","","",0,"",0,)
        SharedHelper.getInstance(context).setDeleteUser(true)
        navController.navigate(Screen.PhoneNumber.route){
            popUpTo(navController.graph.id)
        }
    }

    fun updateUser() {
        closeDialog()
        _loading.value = true
        val old = user
        user.name = _name.value
        user.surname = _surname.value
        user.birthyear = _birthYear.value
        user.regionId = _region.value!!.id
        Api.updateUser(user){
            SharedHelper.getInstance(context).login(user)
            Toast.makeText(context, "Foydalanuvchi ma'umotlari yangilandi", Toast.LENGTH_SHORT).show()
            _loading.value = false
            makeReadOnly()
        }
    }


}