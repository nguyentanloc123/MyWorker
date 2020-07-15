package com.example.myworker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.Contanst.IMethodLoginAnReg
import com.example.myworker.Contanst.IValidate
import com.example.myworker.UserData.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_reg.*


class RegActivity : AppCompatActivity() ,IMethodLoginAnReg,IValidate{
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
                if(validateEqual() && !validate())
                {
                    RegFirebase()
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

    override fun LoginFirebase() {

    }
//email: String, password: String, username: String
    override fun RegFirebase() {
        auth.createUserWithEmailAndPassword(editemail.text.toString(), edtpasswork.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this,"Đăng kí thành công rồi nhen",Toast.LENGTH_LONG).show()
                        // luu vao database
                        val userDatabase = User(editusername.text.toString(), editemail.text.toString(),edtpasswork.text.toString(),false,"")
                        database.child("users").child(editusername.text.toString().toString()).setValue(userDatabase)
                        Log.d("txt",editemail.text.toString())
                        Log.d("txt",editusername.text.toString())
                        Log.d("txt",edtpasswork.text.toString())

                        Toast.makeText(this,editemail.text.toString().toString()
                                + editusername.text.toString()+ edtpasswork.text.toString(),Toast.LENGTH_LONG).show()

                        val sharedPref = getSharedPreferences("",Context.MODE_PRIVATE)
                        with (sharedPref.edit()) {
                            putString(getString(R.string.usernameEmail), editemail.text.toString())
                            putString(getString(R.string.usernameCheck),editusername.text.toString())
                            putString(getString(R.string.usernamePass),edtpasswork.text.toString())
                            commit()
                        }
                        val intent: Intent = Intent(this,XacThucSDTActivity::class.java)
                        intent.putExtra("userName",editusername.text)
                        startActivity(intent)
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

    override fun validate(): Boolean =(editusername.text.isNullOrEmpty() ||
    editemail.text.isNullOrEmpty() || edtpasswork.text.isNullOrEmpty())

    override fun validateEqual(): Boolean = (edtpasswork.text.toString().equals(edtsodienthoai.text.toString()))


}
