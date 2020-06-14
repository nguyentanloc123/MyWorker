package com.example.myworker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myworker.Contanst.IIntentGo
import kotlinx.android.synthetic.main.activity_login_worker.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnloginworker.setOnClickListener {
           startActivity(Intent(this,LoginWorkerActivity::class.java))
        }
        btnloginuser.setOnClickListener {
            startActivity(Intent(this,LoginUserActivity::class.java))
        }
    }
}
