package uni.dev.doctorlink.util

import android.content.Context
import android.content.SharedPreferences

class SharedHelper(context: Context) {
    private val shared: SharedPreferences = context.getSharedPreferences("app_db", Context.MODE_PRIVATE)
    private val edit: SharedPreferences.Editor = shared.edit()

    private val welcome = "welcome"

    companion object{
        private var instance : SharedHelper? = null
        fun getInstance(context: Context): SharedHelper {
            if (instance == null) instance = SharedHelper(context)
            return instance!!
        }
    }

    fun setWelcome(){
        edit.putBoolean(welcome, false).apply()
    }
    fun showWelcome(): Boolean {
        return shared.getBoolean(welcome, true)
    }
}