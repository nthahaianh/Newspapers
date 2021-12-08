package com.example.newspapers.View

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        btn0.setOnClickListener {
            passwordViewModel.addValue(0)
        }
        btn1.setOnClickListener {
            passwordViewModel.addValue(1)
        }
        btn2.setOnClickListener {
            passwordViewModel.addValue(2)
        }
        btn3.setOnClickListener {
            passwordViewModel.addValue(3)
        }
        btn4.setOnClickListener {
            passwordViewModel.addValue(4)
        }
        btn5.setOnClickListener {
            passwordViewModel.addValue(5)
        }
        btn6.setOnClickListener {
            passwordViewModel.addValue(6)
        }
        btn7.setOnClickListener {
            passwordViewModel.addValue(7)
        }
        btn8.setOnClickListener {
            passwordViewModel.addValue(8)
        }
        btn9.setOnClickListener {
            passwordViewModel.addValue(9)
        }
        btnClearAll.setOnClickListener {
            passwordViewModel.clearData()
        }
        btnClearOne.setOnClickListener {
            passwordViewModel.clearOne()
        }
    }

    private fun setUpViewModel() {
        passwordViewModel.isCircle4.observe(this, {
            if (it)
                circle4.setImageResource(R.drawable.ic_full_circle)
            else
                circle4.setImageResource(R.drawable.ic_circle)
        })
        passwordViewModel.isCircle3.observe(this, {
            if (it)
                circle3.setImageResource(R.drawable.ic_full_circle)
            else
                circle3.setImageResource(R.drawable.ic_circle)
        })
        passwordViewModel.isCircle2.observe(this, {
            if (it)
                circle2.setImageResource(R.drawable.ic_full_circle)
            else
                circle2.setImageResource(R.drawable.ic_circle)
        })
        passwordViewModel.isCircle1.observe(this, {
            if (it)
                circle1.setImageResource(R.drawable.ic_full_circle)
            else
                circle1.setImageResource(R.drawable.ic_circle)
        })
        passwordViewModel.isLogin.observe(this, {
            if (it){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
        passwordViewModel.title.observe(this,{
            pass_tvTitle.text = it
        })
    }
}