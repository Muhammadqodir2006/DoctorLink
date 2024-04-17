package uni.dev.doctorlink.screens.hospital

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.model.Doctor
import uni.dev.doctorlink.model.Region
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class HospitalProfileViewModel(val navController: NavController, val context: Context, val hospitalKey: String) {
    private val _name = MutableLiveData("")
    val name :LiveData<String> = _name

    private val _region = MutableLiveData(Api.getRegion(-1))
    val region :LiveData<Region> = _region

    private val _phones = MutableLiveData(listOf<String>())
    val phones :LiveData<List<String>> = _phones

    private val _schedule  =MutableLiveData("")
    val schedule :LiveData<String> = _schedule

    private val _photos = MutableLiveData(listOf<String>())
    val photos :LiveData<List<String>> = _photos

    private val _imageOpen = MutableLiveData(false)
    val imageOpen :LiveData<Boolean> = _imageOpen

    private val _doctorsOFHospital = MutableLiveData(listOf<Doctor>())
    val doctorsOfHospital :LiveData<List<Doctor>> = _doctorsOFHospital

    private val _loadingDoctors = MutableLiveData(true)
    val loadingDoctors :LiveData<Boolean> = _loadingDoctors

    val userKey = SharedHelper.getInstance(context).getUser()!!.key!!

    init {
        getHospitalData()
    }

    private fun getHospitalData() {
        Api.getHospital(hospitalKey){
            _name.value = it.name
            _region.value = Api.getRegion(it.regionId!!)
            _photos.value = it.photo!!
            _schedule.value = it.schedule!!
            _phones.value = it.phone!!
//            Log.d("TAG", "getHospitalData: $it")
        }
        Api.getDoctorsOfHospital(hospitalKey){
            _doctorsOFHospital.value = it
            _loadingDoctors.value = false
        }

    }

    fun openImage() {
        _imageOpen.value = true
    }

    fun onBack() {
        if (_imageOpen.value!!) _imageOpen.value = false
        else navController.popBackStack()
    }
}