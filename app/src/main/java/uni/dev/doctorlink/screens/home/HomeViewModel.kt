package uni.dev.doctorlink.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.navigation.BottomNavScreen

class HomeViewModel(val navController: NavController, val bottomNavController: NavController) {
    private val _searchState = MutableLiveData(false)
    val searchState : LiveData<Boolean> = _searchState

    private val _searchText = MutableLiveData("")
    val searchText : LiveData<String> = _searchText

    private val _dialogOpen = MutableLiveData(false)
    val dialogOpen:LiveData<Boolean> = _dialogOpen

    private val _dialogContent = MutableLiveData(0)
    val dialogContent :LiveData<Int> = _dialogContent

    private val _location = MutableLiveData("Toshkent shahar")
    val location :LiveData<String> = _location

    private val _notificationCount = MutableLiveData(3)
    val notificationCount :LiveData<Int> = _notificationCount

    fun closeSearch(){
        _searchState.value = false
    }
    fun openSearch(){
        _searchState.value = true
    }
    fun updateSearchState(newState: Boolean){
        _searchState.value = newState
    }
    fun updateSearchText(new:String){
        _searchText.value = new
    }
    fun openDialog(){
        _dialogOpen.value = true
    }
    fun closeDialog(){
        _dialogOpen.value = false
    }
    fun updateDialogContent(newContent:Int){
        _dialogContent.value = newContent
    }

    fun changeLocation(newLocation: String) {
        _location.value = newLocation
    }

    fun topLeftButtonAction() {
        bottomNavController.navigate(BottomNavScreen.Profile.route)
    }

}