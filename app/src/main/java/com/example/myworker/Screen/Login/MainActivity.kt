package com.example.myworker.Screen.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myworker.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnloginworker.setOnClickListener {
           startActivity(Intent(this,
               LoginWorkerActivity::class.java))
        }
        btnloginuser.setOnClickListener {
            startActivity(Intent(this,
                LoginUserActivity::class.java))
        }
    }
}
