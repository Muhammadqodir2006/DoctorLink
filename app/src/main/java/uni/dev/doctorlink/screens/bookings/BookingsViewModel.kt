package uni.dev.doctorlink.screens.bookings

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.model.Booking
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class BookingsViewModel(navController: NavController, context: Context) {
    val user = SharedHelper.getInstance(context).getUser()!!

    private val _activeBookings = MutableLiveData(listOf<Booking>())
    val activeBookings: LiveData<List<Booking>> = _activeBookings

    private val _passiveBookings = MutableLiveData(listOf<Booking>())
    val passiveBookings: LiveData<List<Booking>> = _passiveBookings

    private val _showActive = MutableLiveData(true)
    val showActive: LiveData<Boolean> = _showActive

    private val _loadingActive = MutableLiveData(true)
    val loadingActive: LiveData<Boolean> = _loadingActive

    private val _loadingPassive = MutableLiveData(true)
    val loadingPassive: LiveData<Boolean> = _loadingPassive

    private val _showDeleteDialog = MutableLiveData(false)
    val showDeleteDialog :LiveData<Boolean> = _showDeleteDialog

    private val _deleteDialog = MutableLiveData(false)
    val deleteDialog :LiveData<Boolean> = _deleteDialog

    var deleteBookingKey = ""

    init {
        loadActive()
        loadPassive()
    }

    private fun loadPassive() {
        Api.getBookingsOfUser(user.key!!, false) {
            _passiveBookings.value = it
            _loadingPassive.value = false
        }
    }

    private fun loadActive() {
        Api.getBookingsOfUser(user.key!!, true) {
            _activeBookings.value = it
            _loadingActive.value = false
        }
    }

    fun showActive() {
        updateFilter(true)
    }

    fun showPassive() {
        updateFilter(false)
    }

    fun updateFilter(new: Boolean) {
        if (new != _showActive.value) _showActive.value = !_showActive.value!!
    }

    fun openDeleteDialog(bookingKey: String) {
        deleteBookingKey = bookingKey
        _showDeleteDialog.value = true
    }

    fun closeDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun deleteBooking() {
        Api.deleteBooking(deleteBookingKey){
            loadActive()
            loadPassive()
            closeDeleteDialog()
        }
    }


}