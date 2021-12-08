package com.example.newspapers.ViewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class PasswordViewModel(application: Application) : AndroidViewModel(application) {
    var isCircle1 = MutableLiveData<Boolean>()
    var isCircle2 = MutableLiveData<Boolean>()
    var isCircle3 = MutableLiveData<Boolean>()
    var isCircle4 = MutableLiveData<Boolean>()
    var isLogin = MutableLiveData<Boolean>()
    var title = MutableLiveData<String>()

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
    private var password: Int = 0
    var passInput: Int = 0
    var position: Int = 0
    var isCheckSetUp = false

    init {
        password = sharedPreferences.getInt("password", 0)
        val isFirst = sharedPreferences.getBoolean("isFirst", true)
        if (isFirst)
            title.value = "Set up your PIN"
        else
            title.value = "Enter your PIN"
        isCircle1.value = false
        isCircle2.value = false
        isCircle3.value = false
        isCircle4.value = false
    }

    fun addValue(index: Int) {
        when (position) {
            3 -> {
                passInput = passInput * 10 + index
                isCircle4.value = true
                val isFirst = sharedPreferences.getBoolean("isFirst", true)
                if (isFirst)
                    setPassword()
                else
                    checkPass()
            }
            2 -> {
                passInput = passInput * 10 + index
                isCircle3.value = true
                position++

            }
            1 -> {
                passInput = passInput * 10 + index
                isCircle2.value = true
                position++

            }
            else -> {
                passInput = passInput * 10 + index
                isCircle1.value = true
                position++
            }
        }
    }

    private fun setPassword() {
        if (isCheckSetUp) {
            checkSetUp()
        } else {
            password = passInput
            isCheckSetUp = true
            title.value = "Please enter your PIN again to set up"
        }
        clearData()
    }

    private fun checkSetUp() {  // Check pass to set up
        if (password == passInput) {
            var editor = sharedPreferences.edit()
            editor.putInt("password", password)     // Update password to shared preferences
            editor.putBoolean("isFirst", false)
            editor.apply()
            title.value = "Set up password success! Please enter your PIN again to login"
        } else {
            title.value = "Password incorrect! Please set up PIN again"
        }
        isCheckSetUp = false
    }

    private fun checkPass() {   // Check pass to login
        if (password == passInput) {
            isLogin.value = true
            Log.e("checkPass", "Password: $password == $passInput")
        } else {
            isLogin.value = false
            title.value = "Password incorrect! Please enter your PIN again"
        }
        clearData()
    }

    fun clearData() {
        isCircle1.value = false
        isCircle2.value = false
        isCircle3.value = false
        isCircle4.value = false
        passInput = 0
        position = 0
    }

    fun clearOne() {
        when (position) {
            1 -> {
                passInput /= 10
                isCircle1.value = false
                position--
            }
            2 -> {
                passInput /= 10
                isCircle2.value = false
                position--
            }
            3 -> {
                passInput /= 10
                isCircle3.value = false
                position--
            }
        }
    }
}