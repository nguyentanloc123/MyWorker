package com.example.myworker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_user2.*

class XacThucSDTActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.xacthuc)
        textView13.setOnClickListener {
            startActivity(Intent(this,RegActivity::class.java))
        }

    }
}
