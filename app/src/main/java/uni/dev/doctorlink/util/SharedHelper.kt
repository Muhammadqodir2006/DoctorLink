package uni.dev.doctorlink.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uni.dev.doctorlink.model.User

class SharedHelper(context: Context) {
    private val shared: SharedPreferences =
        context.getSharedPreferences("app_db", Context.MODE_PRIVATE)
    private val edit: SharedPreferences.Editor = shared.edit()
    private val gson = Gson()

    private val welcomeKey = "welcome"
    private val userKey = "user"

    companion object {
        private var instance: SharedHelper? = null
        fun getInstance(context: Context): SharedHelper {
            if (instance == null) instance = SharedHelper(context)
            return instance!!
        }
    }

    fun setWelcome() {
        edit.putBoolean(welcomeKey, false).apply()
    }

    fun showWelcome(): Boolean {
        return shared.getBoolean(welcomeKey, true)
    }

    fun login(user: User) {
        val temp = gson.toJson(user)
        edit.putString(userKey, temp).apply()
    }

    fun getUser(): User? {
        val data = shared.getString(userKey, "")!!
        if (data.isBlank()) return null
        val typeToken = object : TypeToken<User>() {}.type
        return gson.fromJson(data, typeToken)
    }

    fun logOut() {
        edit.remove(userKey).apply()
    }
}