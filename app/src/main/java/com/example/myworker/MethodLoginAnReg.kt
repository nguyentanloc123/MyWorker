package com.example.myworker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.Contanst.IMethodLoginAnReg
import com.example.myworker.Contanst.IValidate
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

    private lateinit var database: DatabaseReference
// Initialize Firebase Auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user2)
        //val defaultValue = resources.getInteger(R.integer.saved_high_score_default_key)
        val sharedPref = getSharedPreferences("",Context.MODE_PRIVATE)
        val usernameEmail = sharedPref.getString(getString(R.string.usernameEmail), "username@gmail.com")
        val usernameUser = sharedPref.getString(getString(R.string.usernameUser), "loc dep trai")
         Toast.makeText(this,usernameUser.toString(),Toast.LENGTH_LONG).show()
        Log.d("locqqq",usernameUser.toString())
        database = Firebase.database.reference

        edtemail.setText(usernameEmail.toString())
        auth = FirebaseAuth.getInstance()
        textView13.setOnClickListener {
            startActivity(Intent(this,RegActivity::class.java))
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
    override fun LoginFirebase() {
        val sharedPref = getSharedPreferences("",Context.MODE_PRIVATE)
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
                                        val usernameLogin = ds.getValue<User>(User::class.java)!!.username;
                                        val sodienthoai = ds.getValue<User>(User::class.java)!!.sodienthoai;

                                        Log.d("ttt", usernameLogin)
                                        with (sharedPref.edit())
                                        {
                                            putString(getString(com.example.myworker.R.string.usernameUser), usernameLogin)
                                            putString(getString(com.example.myworker.R.string.usernameSdt), sodienthoai)
                                            commit()
                                        }
                                    }
                                }
                            }
                            override fun onCancelled(databaseError: DatabaseError) {}
                        }
                        usersdRef.addListenerForSingleValueEvent(eventListener)
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "Authentication success.",
                                Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainMap::class.java))
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
