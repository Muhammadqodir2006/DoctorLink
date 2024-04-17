package uni.dev.doctorlink.screens.appointment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper
import java.time.LocalDate

class AppointmentViewModel(
    val navController: NavController, val context: Context, private val doctorKey: String
) {
    val user = SharedHelper.getInstance(context).getUser()


    private val _showExitDialog = MutableLiveData(false)
    val showExitDialog: LiveData<Boolean> = _showExitDialog

    private val _showCompleteDialog = MutableLiveData(false)
    val showCompleteDialog: LiveData<Boolean> = _showCompleteDialog

    private val _date = MutableLiveData(LocalDate.now().plusDays(1)!!)
    val date: LiveData<LocalDate> = _date

    private val _time = MutableLiveData("")
    val time: LiveData<String> = _time

    private val _unavailableTimes = MutableLiveData(listOf<String>())
    val unavailableTimes: LiveData<List<String>> = _unavailableTimes

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    init {
        loadUnavailableTimes()
    }

    private fun loadUnavailableTimes() {
        _loading.value = true
        Api.getBookedTimes(doctorKey, date.value!!) {
            _unavailableTimes.value = it
        }
    }

    fun openExitDialog() {
        _showExitDialog.value = true
    }

    fun closeExitDialog() {
        _showExitDialog.value = false
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun onConfirm() {
        Api.addBooking(
            userKey = user.key!!, doctorKey = doctorKey, date = _date.value!!, time = _time.value!!
        ) {
            _showCompleteDialog.value = true
        }
    }

    fun selectDate(date: LocalDate) {
        _date.value = date
        _time.value = ""
        loadUnavailableTimes()
    }

    fun selectTime(time: String) {
        _time.value = time
    }

}

