package com.example.myworker.Screen.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.Contanst.IMethodLoginAnReg
import com.example.myworker.Contanst.IValidate
import com.example.myworker.R
import com.example.myworker.Screen.Map.MainMap
import com.example.myworker.Screen.Reg.RegActivity
import com.example.myworker.UserData.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login_user2.*

class MethodLoginAnReg : AppCompatActivity() , IMethodLoginAnReg,IValidate{
    private lateinit var auth: FirebaseAuth
    private lateinit var usenameKH: String
    private lateinit var sdtKH: String

    private lateinit var database: DatabaseReference
// Initialize Firebase Auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user2)

    //val defaultValue = resources.getInteger(R.integer.saved_high_score_default_key)

        //val usernameEmail = sharedPref.getString(getString(R.string.usernameEmail), "username@gmail.com")
        //val usernameUser = sharedPref.getString(getString(R.string.usernameUser), "loc dep trai")
         //Toast.makeText(this,usernameUser.toString(),Toast.LENGTH_LONG).show()
        //Log.d("locqqq",usernameUser.toString())
        database = Firebase.database.reference

        //edtemail.setText(usernameEmail.toString())
        auth = FirebaseAuth.getInstance()
        textView13.setOnClickListener {
            startActivity(Intent(this, RegActivity::class.java))
        }

    btnLogin.setOnClickListener {
        LoginFirebase()
    }

    }
    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }
    fun updateUI(account: FirebaseUser?) {
        if (account != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show()
            //startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "U Didnt signed in", Toast.LENGTH_LONG).show()
        }
    }
    fun goMain(uses: String?, sdtt: String?,email: String?, password: String?)
    {
        val sharedPref1 = getSharedPreferences("EMAIL",Context.MODE_PRIVATE)
        val sharedPref2 = getSharedPreferences("PASSWORD",Context.MODE_PRIVATE)
        val sharedPref = getSharedPreferences("",Context.MODE_PRIVATE)
        with (sharedPref1.edit())
        {
            putString(getString(R.string.usernameEmail), email)
            commit()
        }
        with (sharedPref2.edit())
        {
            putString(getString(R.string.usernamePass), password)
            commit()
        }

        Log.d("tttt1", uses)
        Log.d("tttt2", sdtt)
        with (sharedPref.edit())
        {
            putString(getString(R.string.usernameUser), uses)
            commit()
        }


        startActivity(Intent(this, MainMap::class.java))
    }
    override fun LoginFirebase() {
        auth.signInWithEmailAndPassword(edtemail.text.toString(), passwork.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val usersdRef: DatabaseReference = database.child("users")
                        val eventListener: ValueEventListener = object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (ds in dataSnapshot.children) {
                                    val emailtemp =ds.getValue<User>(User::class.java)!!.email;
                                    //  val temp = edtemail.text.toString()
                                    if(emailtemp.toString().equals(edtemail.text.toString()))
                                    {
                                        var usernameLogin = ds.getValue<User>(User::class.java)!!.username;

                                        var sodienthoai = ds.getValue<User>(User::class.java)!!.sodienthoai;
                                        usenameKH = ds.getValue<User>(User::class.java)!!.username.toString();
                                        sdtKH =  ds.getValue<User>(User::class.java)!!.sodienthoai.toString()
                                        Log.d("ttt1", usernameLogin)
                                        Log.d("ttt2", sodienthoai)
                                        val sharedPref1 = getSharedPreferences("SODIENTHOAI",Context.MODE_PRIVATE)
                                        with (sharedPref1.edit())
                                        {
                                            putString(getString(R.string.usernameSdt), ds.getValue<User>(User::class.java)!!.sodienthoai)
                                            commit()
                                        }

                                        goMain(usernameLogin,sodienthoai,edtemail.text.toString(), passwork.text.toString())


                                    }
                                }
                            }
                            override fun onCancelled(databaseError: DatabaseError) {}
                        }
                        usersdRef.addListenerForSingleValueEvent(eventListener)
//                        val user = auth.currentUser
//
//                        Toast.makeText(baseContext, user.toString(),
//                                Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }

    }

    override fun RegFirebase() {

    }

    override fun validate(): Boolean {
        return true
    }

    override fun validateEqual(): Boolean {
        return true
    }

}
