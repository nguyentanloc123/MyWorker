package com.example.myworker.Screen.OTP

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.R
import com.example.myworker.Screen.Login.LoginWorkerActivity
import com.example.myworker.Screen.Login.MethodLoginAnReg
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_o_t_p.*
import java.util.concurrent.TimeUnit


class OTPActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private var verificationId: String? = null
    private var sdtReceiver:String?= null
    private var userName:String?= null

    private var manhinh:String? =null
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_t_p)
        mAuth = FirebaseAuth.getInstance();
        database = Firebase.database.reference

        val intent = intent
        sdtReceiver= intent.getStringExtra("sodienthoaiOTP")
        manhinh = intent.getStringExtra("manhinh")
        userName= intent.getStringExtra("userName")
        sendVerificationCode(sdtReceiver.toString());

        button2.setOnClickListener {
            val code: String = t1.getText().toString().trim()

            if (code.isEmpty() || code.length < 6) {
                t1.setError("Enter code...")
                t1.requestFocus()
            }
            verifyCode(code)

        }


    }
    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // lưu sdt vào cache datavase
                    val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
                    val sdt = sharedPref.getString(getString(R.string.usernameSdt), "")
                    with (sharedPref.edit()) {
                        putString(getString(R.string.usernameSdt),sdtReceiver.toString())
                        commit()
                    }
                    if(manhinh.equals("1"))
                    {
                        val intent = Intent(this, LoginWorkerActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    else if (manhinh.equals("2"))
                    {
                        val usernameUser = sharedPref.getString(R.string.usernameUser.toString(), "loc dep trai")


                        val intent = Intent(this, MethodLoginAnReg::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }

                } else {
                    Toast.makeText(
                        this,
                        task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun sendVerificationCode(number: String) {
       // progressBar.setVisibility(View.VISIBLE)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+84" + number,
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallBack
        )
    }

    private val mCallBack: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                s: String,
                forceResendingToken: ForceResendingToken
            ) {
                super.onCodeSent(s, forceResendingToken)
                verificationId = s
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {

                    t1.setText(code.get(0).toString())
                    t2.setText(code.get(1).toString())
                    t3.setText(code.get(2).toString())
                    t4.setText(code.get(3).toString())
                    t5.setText(code.get(4).toString())
                    t6.setText(code.get(5).toString())
                  //  editText3.setText(code)
                    verifyCode(code)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
               // Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

}
