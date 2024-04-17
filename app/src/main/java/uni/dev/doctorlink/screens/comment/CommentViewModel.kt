package uni.dev.doctorlink.screens.comment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import uni.dev.doctorlink.model.Comment
import uni.dev.doctorlink.util.Api
import uni.dev.doctorlink.util.SharedHelper

class CommentViewModel(val navController: NavController, val context: Context, val bookingKey: String, val doctorKey: String) {
    private val commentMaxLength = 1000

    private val _rating = MutableLiveData(3)
    val rating: LiveData<Int> = _rating

    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    private val _exitDialogOpen = MutableLiveData(false)
    val exitDialogOpen: LiveData<Boolean> = _exitDialogOpen

    private val _successDialogOpen = MutableLiveData(false)
    val successDialogOpen :LiveData<Boolean> = _successDialogOpen

    fun changeRating(new: Int) {
        _rating.value = new
    }

    fun changeText(new: String) {
        if (new.length < commentMaxLength) _text.value = new
    }

    fun onBack() {
        _exitDialogOpen.value = true
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun onConfirmRate() {
        val userKey = SharedHelper.getInstance(context).getUser().key!!
        val comment = Comment(userKey = userKey, doctorKey = doctorKey, text = _text.value!!, rating = _rating.value!!)
        Api.addComment(comment)
        Api.makeCommented(bookingKey){
            _successDialogOpen.value = true
        }
    }

    fun closeExitDialog() {
        _exitDialogOpen.value = false
    }
}