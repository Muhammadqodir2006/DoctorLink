package uni.dev.doctorlink.screens.userDetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.model.Region
import uni.dev.doctorlink.model.User
import uni.dev.doctorlink.navigation.Screen
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class UserDetailsViewModel(val navController: NavController, val phone: String, val context: Context) {

    private var _name = MutableLiveData("")
    var name : LiveData<String> =_name

    private var _surname = MutableLiveData("")
    var surname : LiveData<String> =_surname

    private var _birthYear = MutableLiveData(0)
    var birthYear :LiveData<Int> = _birthYear

    private val _dialogOpen = MutableLiveData(false)
    val dialogOpen : LiveData<Boolean> = _dialogOpen

    private val _dialogContent = MutableLiveData(0)
    val dialogContent :LiveData<Int> = _dialogContent

    private val _region = MutableLiveData(Region(-1, ""))
    val region :LiveData<Region> = _region

    private val _loading = MutableLiveData(false)
    val loading :LiveData<Boolean> = _loading



    fun updateName(new: String){
        if (new.length <= 35) {
            _name.value = new
        }
    }

    fun closeDialog(){
        _dialogOpen.value = false
    }

    fun updateDialogContent(it: Int) {
        _dialogContent.value = it
    }

    fun updateSurname(new: String) {
        if (new.length <= 35) {
            _surname.value = new
        }
    }

    fun updateBirthYear(year: Int) {
        _birthYear.value = year
    }

    fun openDialog() {
        _dialogOpen.value = true
    }

    fun onContinue() {
        _loading.value = true
        Api.register(User(name = _name.value, surname = _surname.value, phone = phone, birthyear = _birthYear.value, regionId = _region.value!!.id)){
            SharedHelper.getInstance(context).login(it)
            _loading.value = false
            navController.navigate(Screen.Main.route){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }
    }

    fun updateRegion(reg: Region) {
        _region.value = reg
    }
}