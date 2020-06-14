package com.example.myworker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.myworker.UserData.WorkerData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login_worker.*



class LoginWorkerActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    var cmndd: String? = "jjjj"
    var passworkk:String? = "oooo"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_worker)
        database = Firebase.database.reference
        val view: View = findViewById(R.id.tabBarBack)
        val btnback: TextView= view.findViewById(R.id.btnBack)
        btnback.setOnClickListener {
            onBackPressed()
        }



//   val cmnd = username.text.toString()
        button3.setOnClickListener {
            val usersdRef: DatabaseReference = database.child("worker")
            val eventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.cmnd
                        var tpassworkk =ds.getValue<WorkerData>(WorkerData::class.java)!!.passwork
                        if (username.text.toString().equals(tcmndd) && tpassworkk.equals(editText.text.toString()))
                        {
                            cmndd = tcmndd
                            passworkk = tpassworkk
                            goMainMap()

                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            }
            usersdRef.addListenerForSingleValueEvent(eventListener)
            Log.d("tk", cmndd+" "+ passworkk)

        }
    }
    fun goMainMap()
    {
        startActivity(Intent(this,MainMap::class.java))
    }
}
