package com.example.myworker.Tho

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myworker.R
import com.example.myworker.UserData.UserWorkAdd
import com.example.myworker.UserData.WorkerData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sua_xong.*
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.*

class SuaXongActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sua_xong)
        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
        val userName = sharedPref.getString(getString(R.string.usernameTho), "Khách hàng")
        database = Firebase.database.reference
        val sharedPref1 = getSharedPreferences("IDTHO_1", Context.MODE_PRIVATE)
        val idTho = sharedPref1.getString(getString(R.string.idTho),"123")

        btnHoanTat.setOnClickListener {

            val usersdRef: DatabaseReference = database.child("worker")
            val eventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;
                        Log.d("id12",tcmndd)
                        if(tcmndd.equals(idTho))
                        {
                            val key = database.push().key
                            val post = ds.child("datatemp")
                            var locc = post.getValue<UserWorkAdd>(UserWorkAdd::class.java)!!
                            database.child("worker").child(userName.toString()).child("datatemp").child("tinhtranghuhai").setValue("HT")
                            database.child("worker").child(userName.toString()).child("lichsusuachua").child(key.toString()).setValue(locc)

                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            }
            usersdRef.addListenerForSingleValueEvent(eventListener)


            //database.child("worker").child(userName.toString()).child("datatemp").child("tinhtranghuhai").setValue("HT")

            //database.child("worker").child(userName.toString()).child("datatemp").child("tinhtranghuhai").setValue("HT")



        }
    }
}