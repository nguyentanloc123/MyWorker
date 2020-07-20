package com.example.myworker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.myworker.Contanst.IMethodLoginAnReg
import com.example.myworker.Contanst.IValidate
import com.example.myworker.UserData.User
import com.example.myworker.UserData.WorkerData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dang_ki_tho.*
import kotlinx.android.synthetic.main.activity_login_worker.*



class LoginWorkerActivity : AppCompatActivity() ,IMethodLoginAnReg,IValidate{
    private lateinit var database: DatabaseReference
    var cmndd: String? = "jjjj"
    var passworkk:String? = "oooo"

    lateinit var workerData1: WorkerData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_worker)
        database = Firebase.database.reference
        val view: View = findViewById(R.id.tabBarBack)
        val btnback: TextView= view.findViewById(R.id.btnBack)
        btnback.setOnClickListener {
            onBackPressed()
        }
        textView7.setOnClickListener {
            startActivity(Intent(this,DangKiThoActivity::class.java))
        }



//   val cmnd = username.text.toString()
        button3.setOnClickListener {
            LoginFirebase()
        }
    }
    fun goMainMap()
    {

        startActivity(Intent(this,MainTho::class.java))
    }

    override fun LoginFirebase() {
        val usersdRef: DatabaseReference = database.child("worker")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.userName;
                    Log.d("email",tcmndd)
                    var tpassworkk =ds.getValue<WorkerData>(WorkerData::class.java)!!.passwork;
                    var idTho =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;

                    Log.d("email",tpassworkk)
                    if (username.text.toString().equals(tcmndd) && tpassworkk.equals(editText.text.toString()))
                    {
                        cmndd = tcmndd
                        passworkk = tpassworkk

                        val sharedPref1 = getSharedPreferences("IDTHO_1", Context.MODE_PRIVATE)
                        with(sharedPref1.edit()){
                            putString(getString(R.string.idTho),ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho)
                            commit()
                        }
                        Log.d("id11",idTho)



                        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
                        with (sharedPref.edit()) {
                            putString(getString(R.string.emailTho), tcmndd)
                            putString(getString(R.string.usernameTho), ds.getValue<WorkerData>(WorkerData::class.java)!!.userName.toString())
                            commit()
                        }
                        Toast.makeText(baseContext, "Authentication success.",
                                Toast.LENGTH_SHORT).show()
                        goMainMap()
                    }

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersdRef.addListenerForSingleValueEvent(eventListener)


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
