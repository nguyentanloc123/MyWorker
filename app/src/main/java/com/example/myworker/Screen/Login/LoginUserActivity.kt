package com.example.myworker.Screen.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myworker.R
import com.example.myworker.Screen.Reg.RegActivity
import kotlinx.android.synthetic.main.activity_login_user.*

class LoginUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)
        btnLogin.setOnClickListener {
            startActivity(Intent(this, MethodLoginAnReg::class.java))
        }
        button.setOnClickListener {
            startActivity(Intent(this, RegActivity::class.java))
        }
    }
}
