package com.mis.route.e_commerce.data.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.mis.route.e_commerce.data.api.model.response.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefsHelper @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        const val PREFS_NAME = "app_prefs"
        const val TOKEN_KEY = "token"
        const val USER_KEY = "user"
    }

    val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun saveToken(token: String?) {
        token?.let { token ->
            sharedPrefs.edit().putString(TOKEN_KEY, token).apply()
        }

    }

    fun saveUser(user: User?) {
        user.let { user ->
            val gson = Gson()
            val userAsString = gson.toJson(user).toString()
            Log.e("SAVE USER", userAsString)
            sharedPrefs.edit().putString(USER_KEY, userAsString).apply()

        }
    }

    fun getToken(): String? {
        return sharedPrefs.getString(TOKEN_KEY, null)
    }

    fun getUser(): User? {
        val gson = Gson()
        val savedUser =
            sharedPrefs.getString(USER_KEY, null)
        savedUser?.let {
            val user: User = gson.fromJson<User>(it, User::class.java)
            return user
        }
        return null
    }
}