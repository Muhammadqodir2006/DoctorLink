package uni.dev.doctorlink.screens.bookings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BookingsViewModel {
    private val _filter = MutableLiveData(1)
    val filter :LiveData<Int> = _filter

    fun updateFilter(num:Int){
        if (num != _filter.value) {
            _filter.value = num
        }

    }
}