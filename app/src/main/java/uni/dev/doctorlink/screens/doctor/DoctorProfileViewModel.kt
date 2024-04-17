package uni.dev.doctorlink.screens.doctor

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.model.Comment
import uni.dev.doctorlink.model.Hospital
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper
import java.time.LocalDate

class DoctorProfileViewModel(val navController: NavController, val doctorKey: String, context: Context) {
    val user = SharedHelper.getInstance(context).getUser()

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _surname = MutableLiveData("")
    val surname: LiveData<String> = _surname

    private val _image = MutableLiveData("")
    val image: LiveData<String> = _image


    private val _rating = MutableLiveData(0f)
    val rating: LiveData<Float> = _rating

    private val _price = MutableLiveData(0)
    val price: LiveData<Int> = _price


    private val _specialties = MutableLiveData(listOf<Int>())
    val specialties: LiveData<List<Int>> = _specialties

    private val _info = MutableLiveData("")
    val info: LiveData<String> = _info

    private val _regionId = MutableLiveData(-1)
    val regionId: LiveData<Int> = _regionId

    private val _hospital = MutableLiveData(Hospital("", "", -1, listOf(), "", listOf()))
    val hospital: LiveData<Hospital> = _hospital

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private val _loadingComments = MutableLiveData(true)
    val loadingComments: LiveData<Boolean> = _loadingComments

    private val _experience = MutableLiveData(0)
    val experience: LiveData<Int> = _experience

    private val _comments = MutableLiveData(listOf<Comment>())
    val comments: LiveData<List<Comment>> = _comments

    init {
        loadDoctorData()
        loadComments()
    }

    private fun loadComments() {
        Api.getComments(doctorKey) {
            _comments.value = it
            _loadingComments.value = false
        }
    }

    private fun loadDoctorData() {
        Api.getDoctor(doctorKey) {
            _name.value = it.name
            _surname.value = it.surname
            _image.value = it.image
            _rating.value = it.rating
            _price.value = it.price
            _specialties.value = it.specialty
            _info.value = it.info
            _regionId.value = it.regionId
            val currentYear = LocalDate.now().year
            _experience.value = currentYear - it.yearStarted!!
            loadHospital(it.hospitalKey!!)
            _loading.value = false
            Log.d("TAG", "loadDoctorData: ${it.key}")
        }
    }

    private fun loadHospital(hospitalKey: String) {
        Api.getHospital(hospitalKey) {
            _hospital.value = it
        }
    }

    fun backButtonClick() {
        navController.popBackStack()
    }

    fun register() {
        navController.navigate("appointment/$doctorKey")
    }


}