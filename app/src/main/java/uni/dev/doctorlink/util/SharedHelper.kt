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
    private val searchHistoryKey  = "searchHistory"
    private val delete  = "delete"

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
    fun setDeleteUser(new:Boolean) {
        edit.putBoolean(delete, new).apply()
    }

    fun getDeleteUser(): Boolean {
        return shared.getBoolean(delete, false)
    }

    fun login(user: User) {
        val temp = gson.toJson(user)
        edit.remove(userKey)
        edit.putString(userKey, temp).apply()
    }

    fun getUser(): User {
        val data = shared.getString(userKey, "")!!
        if (data.isBlank()) return User(phone = "", name = "", regionId = 0, surname = "", birthyear = 0)
        val typeToken = object : TypeToken<User>() {}.type
        return gson.fromJson(data, typeToken)
    }

    fun logOut() {
        edit.putString(userKey, gson.toJson(User())).apply()
    }

    fun getSearchHistory(): List<String> {
        val data = shared.getString(searchHistoryKey, "")!!
        if (data == "") return listOf()
        val typeToken = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, typeToken)

    }

    fun addToSearchHistory(new:String){
        var history = getSearchHistory().toMutableList()
        if (history.contains(new)) history.remove(new)
        history.add(0, new)
        if (history.size>10) history = history.dropLast(1).toMutableList()
        setSearchHistory(history)
    }



    private fun setSearchHistory(list: List<String>){
        val temp = gson.toJson(list)
        edit.putString(searchHistoryKey, temp).apply()
    }
}