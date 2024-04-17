package uni.dev.doctorlink.screens.notifications

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.model.Booking
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class NotificationsViewModel(val navController: NavController, val context: Context) {
    val user = SharedHelper.getInstance(context).getUser()!!
    private val _uncommentedBookings = MediatorLiveData(listOf<Booking>())
    val uncommentedBookings: LiveData<List<Booking>> = _uncommentedBookings

    private val _loading = MediatorLiveData(false)
    val loading: LiveData<Boolean> = _loading

    init {
        loadUncommented()
    }

    private fun loadUncommented() {
        _loading.value = true
        Api.getUncommentedBookings(userKey = user.key!!) {
            _uncommentedBookings.value = it
            _loading.value = false
        }
    }

    fun onBack() {
        navController.popBackStack()
    }

    fun makeCommented(bookingKey: String) {
        Api.makeCommented(bookingKey){}
    }

    fun goToComment(it: Booking) {
        navController.navigate("comment/${it.key!!}/${it.doctorKey!!}")
    }
}