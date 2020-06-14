package com.example.myworker

import android.R.array
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.UserData.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_reg.*


class RegActivity : AppCompatActivity() {
   // public  lateinit var database: DatabaseReference
   private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
//    public final val PREFS_FILENAME = "ccom.example.myworker.prefs"
//    var prefs: SharedPreferences? = null
//    val USERLOCAL = "user local"


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_reg)
        database = Firebase.database.reference

        auth = FirebaseAuth.getInstance()

//            database = Firebase.database.reference
        val randomValues = (0..1000).random()

          Toast.makeText(this,randomValues.toString(),Toast.LENGTH_LONG).show()
            btnReg.setOnClickListener {
                if(checkValidateEqual() && !checkValidateNullorEmpty())
                {
                    writeNewUser(editemail.text.toString(),edtpasswork.text.toString(),editusername.text.toString())
                }
                else
                {
                    Toast.makeText(this,"Không đăng nhập được",Toast.LENGTH_LONG).show()
                }
            }

        }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
      //  updateUI(currentUser)
    }

    private fun writeNewUser(email: String,password:String,username: String?) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this,"Đăng kí thành công rồi nhen",Toast.LENGTH_LONG).show()
                    // luu vao database
                    val userDatabase = User(username, email,password,false)
                    database.child("users").child(username.toString()).setValue(userDatabase)

//                    database.child("users").child(username.toString()).addValueEventListener(object : ValueEventListener {
//                        override fun onDataChange(dataSnapshot: DataSnapshot) {
//                            val user = dataSnapshot.getValue<User>(User::class.java)
//                            Log.d("database",user.toString())
//                        }
//
//                        override fun onCancelled(error: DatabaseError) { // Failed to read value
//
//                        }
//                    })
                    val sharedPref = getSharedPreferences("",Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString(getString(R.string.usernameEmail), email)
                        putString(getString(R.string.usernameUser),username)
                        putString(getString(R.string.usernamePass),password)
                        commit()
                    }

                    startActivity(Intent(this,LoginUser::class.java))
                    // luu vao sharepreference

                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                   // updateUI(null)
                }

                // ...
            }

    }
     fun checkValidateNullorEmpty() : Boolean = (editusername.text.isNullOrEmpty() ||
            editemail.text.isNullOrEmpty() || edtpasswork.text.isNullOrEmpty())
    fun checkValidateEqual() : Boolean= (edtpasswork.text.toString().equals(editpassworkre.text.toString()))


}
