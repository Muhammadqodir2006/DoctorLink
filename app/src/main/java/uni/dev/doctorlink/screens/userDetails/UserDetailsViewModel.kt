package uni.dev.doctorlink.screens.userDetails

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.navigation.Screen

class UserDetailsViewModel(val navController: NavController) {

    private var _name = MutableLiveData("")
    var name : LiveData<String> =_name

    private var _surname = MutableLiveData("")
    var surname : LiveData<String> =_surname

    private var _gender = MutableLiveData(true)
    var gender : LiveData<Boolean> =_gender

    private var _birthYear = MutableLiveData(0)
    var birthYear :LiveData<Int> = _birthYear

    private val _dialogOpen = MutableLiveData(false)
    val dialogOpen : LiveData<Boolean> = _dialogOpen

    private val _dialogContent = MutableLiveData(0)
    val dialogContent :LiveData<Int> = _dialogContent

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

    fun updateGender(new: Boolean) {
        _gender.value = new
    }

    fun updateBirtYear(year: Int) {
        _birthYear.value = year
    }

    fun openDialog() {
        _dialogOpen.value = true
    }

    fun onContinue() {
        // TODO
        navController.navigate(Screen.Main.route)
    }


}