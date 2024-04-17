package uni.dev.doctorlink.screens.saved

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import uni.dev.doctorlink.model.Doctor
import uni.dev.doctorlink.model.Hospital
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class SavedViewModel(val navController: NavHostController, context: Context) {

    private val _savedDoctors = MutableLiveData(listOf<Doctor>())
    val savedDoctors: LiveData<List<Doctor>> = _savedDoctors

    private val _savedHospitals = MutableLiveData(listOf<Hospital>())
    val savedHospitals: LiveData<List<Hospital>> = _savedHospitals

    private val _doctorsLoading = MutableLiveData(true)
    val doctorsLoading: LiveData<Boolean> = _doctorsLoading

    private val _hospitalsLoading = MutableLiveData(true)
    val hospitalsLoading: LiveData<Boolean> = _hospitalsLoading

    private val _doctorsOpen = MutableLiveData(true)
    val doctorsOpen: LiveData<Boolean> = _doctorsOpen

    val user = SharedHelper.getInstance(context).getUser()!!

    init {
        loadSavedDoctors()
        loadSavedHospitals()
    }

    private fun loadSavedDoctors() {
        Api.getSavedKeys(user.key!!, true) { keys ->
            val new = mutableListOf<Doctor>()
            if (keys.isEmpty()) {
                _doctorsLoading.value = false
                _savedDoctors.value = listOf()
            } else keys.forEachIndexed { index, k ->
                Api.getDoctor(k) { doctor ->
                    new.add(doctor)
                    if (index == keys.size - 1) _savedDoctors.value = new
                }
            }
        }
    }

    private fun loadSavedHospitals() {
        Api.getSavedKeys(user.key!!, false) { keys ->
            val new = mutableListOf<Hospital>()
            if (keys.isEmpty()) {
                _hospitalsLoading.value = false
                _savedHospitals.value = listOf()
            } else keys.forEachIndexed { index, k ->
                Api.getHospital(k) { hospital ->
                    new.add(hospital)
                    if (index == keys.size - 1) _savedHospitals.value = new
                }
            }
        }
    }

    fun openHospitals() {
        _doctorsOpen.value = false
    }

    fun openDoctors() {
        _doctorsOpen.value = true
    }

}