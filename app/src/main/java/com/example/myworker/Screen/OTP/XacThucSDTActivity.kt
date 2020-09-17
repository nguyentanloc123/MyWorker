package com.example.myworker.Screen.OTP

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.R
import kotlinx.android.synthetic.main.xacthuc.*
import java.util.regex.Pattern


class XacThucSDTActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.xacthuc)


        val intent =intent
        var userName =intent.getStringExtra("userName")
        button2.setOnClickListener {

            if(isValidPhone(editText3.getText().toString())){
                val intent: Intent = Intent(this,
                    OTPActivity::class.java)
                intent.putExtra("sodienthoaiOTP",editText3.text.toString())
                intent.putExtra("manhinh","2")
                intent.putExtra("userName",userName)
                startActivity(intent)
               // Toast.makeText(getApplicationContext(),"Phone number is valid",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Phone number is not valid",Toast.LENGTH_SHORT).show();

            }


        }

    }
    private fun isValidPhone(phone: String): Boolean {
        var check = false
        check = if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length < 10 || phone.length > 11) {
                false
            } else {
                true
            }
        } else {
            false
        }
        return check
    }

}
