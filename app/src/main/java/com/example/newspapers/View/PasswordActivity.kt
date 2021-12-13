package com.example.newspapers.View

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.children
import androidx.lifecycle.ViewModelProviders
import com.example.newspapers.R
import com.example.newspapers.ViewModel.PasswordViewModel
import kotlinx.android.synthetic.main.activity_password.*

@SuppressLint("SetTextI18n")
class PasswordActivity : AppCompatActivity() {
    lateinit var passwordViewModel: PasswordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        passwordViewModel = ViewModelProviders.of(this).get(PasswordViewModel::class.java)
        setUpViewModel()
        setUpView()
    }

    private fun setUpView() {
        btnClearAll.setOnClickListener {
            passwordViewModel.clearData()
        }
        btnClearOne.setOnClickListener {
            passwordViewModel.clearOne()
        }
    }

    private fun setUpViewModel() {
        passwordViewModel.isCircle4.observe(this, {
            setResource(it, circle4)
        })
        passwordViewModel.isCircle3.observe(this, {
            setResource(it, circle3)
        })
        passwordViewModel.isCircle2.observe(this, {
            setResource(it, circle2)
        })
        passwordViewModel.isCircle1.observe(this, {
            setResource(it, circle1)
        })
        passwordViewModel.isLogin.observe(this, {
            if (it) {
                finish()
            }
        })
        passwordViewModel.title.observe(this, {
            pass_tvTitle.text = it
        })
    }

    private fun setResource(it: Boolean, dot: ImageView) {
        if (it)
            dot.setImageResource(R.drawable.ic_full_circle)
        else
            dot.setImageResource(R.drawable.ic_circle)
    }

    fun onClickButton(view: View) {
        val button = view as AppCompatButton
        when (button.text.toString()) {
            "0" -> passwordViewModel.addValue(0)
            "1" -> passwordViewModel.addValue(1)
            "2" -> passwordViewModel.addValue(2)
            "3" -> passwordViewModel.addValue(3)
            "4" -> passwordViewModel.addValue(4)
            "5" -> passwordViewModel.addValue(5)
            "6" -> passwordViewModel.addValue(6)
            "7" -> passwordViewModel.addValue(7)
            "8" -> passwordViewModel.addValue(8)
            "9" -> passwordViewModel.addValue(9)
        }
    }

    override fun onBackPressed() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Are you sure?")
            .setMessage("Do you want to exit app?")
            .setNegativeButton("No") { _: DialogInterface, _: Int -> }
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                finishAffinity()
            }
            .show()
    }
}