package uni.dev.doctorlink.screens.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.model.Doctor
import uni.dev.doctorlink.model.Hospital
import uni.dev.doctorlink.model.Region
import uni.dev.doctorlink.navigation.BottomNavScreen
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class HomeViewModel(
    val navController: NavController, private val bottomNavController: NavController, context: Context
) {
    val shared = SharedHelper.getInstance(context)
    val user = shared.getUser()

    private val _layoutState = MutableLiveData(0)
    val layoutState: LiveData<Int> = _layoutState

    private val _searchText = MutableLiveData("")
    val searchText: LiveData<String> = _searchText

    private val _regionDialogOpen = MutableLiveData(false)
    val regionDialogOpen: LiveData<Boolean> = _regionDialogOpen

    private val _dialogContent = MutableLiveData(0)
    val dialogContent: LiveData<Int> = _dialogContent

    private val _notificationCount = MutableLiveData(0)
    val notificationCount: LiveData<Int> = _notificationCount

    private val _topHospitals = MutableLiveData(listOf<Hospital>())
    val topHospitals: LiveData<List<Hospital>> = _topHospitals

    private val _topDoctors = MutableLiveData(listOf<Doctor>())
    val topDoctors: LiveData<List<Doctor>> = _topDoctors

    private val _loadingTopDoctors = MutableLiveData(false)
    val loadingTopDoctors: LiveData<Boolean> = _loadingTopDoctors

    private val _loadingTopHospitals = MutableLiveData(false)
    val loadingTopHospitals: LiveData<Boolean> = _loadingTopDoctors

    private val _region = MutableLiveData(Api.getRegion(user.regionId!!))
    val region: LiveData<Region> = _region

    private val _showDoctors = MutableLiveData(true)
    val secondLayoutShowDoctors: LiveData<Boolean> = _showDoctors

    private var allDoctors = mutableListOf<Doctor>()
    private var allHospitals = mutableListOf<Hospital>()

    private val _secondLayoutRegionFilterId = MutableLiveData(user.regionId!!)
    val secondLayoutRegionFilterId: LiveData<Int> = _secondLayoutRegionFilterId

    private val _showLoc = MutableLiveData(false)
    val secondLayShowLoc: LiveData<Boolean> = _showLoc

    var oldLayoutState = 0

    private val _searchHistory = MutableLiveData(shared.getSearchHistory())
    val searchHistory: LiveData<List<String>> = _searchHistory

    private val _searchedState = MutableLiveData(false)
    val searchedState :LiveData<Boolean> = _searchedState

    private val _searchedDoctors = MutableLiveData(listOf<Doctor>())
    val  searchedDoctors :LiveData<List<Doctor>> = _searchedDoctors

    private val _searchedHospitals = MutableLiveData(listOf<Hospital>())
    val searchedHospitals :LiveData<List<Hospital>> = _searchedHospitals

    init {
        changeLayoutState(0)
        loadHospitals()
        loadDoctors()
        loadUncommented()
    }

    private fun loadUncommented() {
        Api.getUncommentedBookings(shared.getUser().key!!){
            _notificationCount.value = it.size
        }
    }


    fun search(key: String) {
        _searchText.value = key
        _searchedState.value = true
        if (_searchText.value!!.isNotBlank()){
            val doctors = mutableListOf<Doctor>()
            allDoctors.forEach {doctor ->
                if ((doctor.name + doctor.surname).lowercase().contains(key.lowercase())) doctors.add(doctor)
            }
            val hospitals = mutableListOf<Hospital>()
            allHospitals.forEach {hospital ->
                if (hospital.name!!.lowercase().contains(key.lowercase())) hospitals.add(hospital)
            }
            _searchedHospitals.value = hospitals
            _searchedDoctors.value = doctors
        }
        addToSearchHistory(key)
    }

    private fun addToSearchHistory(new: String) {
        shared.addToSearchHistory(new)
        _searchHistory.value = shared.getSearchHistory()
    }




    private fun filterByRegion() {
        if (_secondLayoutRegionFilterId.value == -1) {
            _topDoctors.value = allDoctors
            _topHospitals.value = allHospitals
        } else {
            val doctors = mutableListOf<Doctor>()
            allDoctors.forEach { if (it.regionId!! == _secondLayoutRegionFilterId.value) doctors.add(it) }
            _topDoctors.value = doctors

            val hospitals = mutableListOf<Hospital>()
            allHospitals.forEach { if (it.regionId!! == _secondLayoutRegionFilterId.value) hospitals.add(it) }
            _topHospitals.value = hospitals
        }
    }


    fun changeRegionFilterId(new: Int) {
        _secondLayoutRegionFilterId.value = new
        _showLoc.value = new == -1
    }

    private fun changeLayoutState(new: Int) {
        oldLayoutState = _layoutState.value!!
        _layoutState.value = new
    }

    fun openSearch() {
        changeLayoutState(1)
    }
    fun closeSearch() {
        changeLayoutState(oldLayoutState)
        _searchText.value = ""
    }


    fun updateSearchText(new: String) {
        _searchText.value = new
    }

    private fun openSecond(showDoctors: Boolean) {
        _showDoctors.value = showDoctors
        changeLayoutState(2)
        changeRegionFilterId(_region.value!!.id!!)
    }

    fun openAllHospitals() {
        openSecond(false)
    }

    fun openAllDoctors() {
        openSecond(true)
    }

    fun closeSecond() {
        changeLayoutState(0)
    }

    fun closeRegionDialog() {
        _regionDialogOpen.value = false
    }

    fun openRegionDialog() {
        _dialogContent.value = 0
        _regionDialogOpen.value = true
    }
    fun changeLocation(newRegion: Region) {
        if (_region.value!! != newRegion) {
            _region.value = newRegion
            setTopHospitals()
            setTopDoctors()
        }
    }

    fun topLeftButtonAction() {
        bottomNavController.navigate(BottomNavScreen.Profile.route)
    }

    fun loadHospitals() {
        _loadingTopHospitals.value = true
        Api.getAllHospitals {
            _loadingTopHospitals.value = false
            allHospitals = it.toMutableList()
            setTopHospitals()
            Log.d("TAG", "loadHospitals: ${allHospitals.size}")
        }
    }

    fun loadDoctors() {
        _loadingTopDoctors.value = true
        Api.getAllDoctors {
            _loadingTopDoctors.value = false
            allDoctors = it.toMutableList()
            setTopDoctors()
            Log.d("TAG", "loadDoctors: ${allDoctors.size}")
        }
    }

    private fun setTopHospitals() {
        val list = mutableListOf<Hospital>()
        allHospitals.forEach { if (it.regionId == _region.value!!.id) list.add(it) }
        _topHospitals.value = list
    }

    private fun setTopDoctors() {
        val list = mutableListOf<Doctor>()
        allDoctors.forEach { if (it.regionId == _region.value!!.id) list.add(it) }
        _topDoctors.value = list
        Log.d("TAG", "setTopDoctors: ${list.size}")
    }

}

