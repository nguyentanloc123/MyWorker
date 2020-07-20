package com.example.myworker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myworker.UserData.UserWorkAdd
import com.example.myworker.UserData.WorkerData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dang_ki_tho.*
import kotlinx.android.synthetic.main.activity_main_tho.*
import kotlinx.android.synthetic.main.activity_waiting_tho.*
import kotlinx.android.synthetic.main.activity_waiting_tho.btnHuy

class WaitingTho : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_tho)
        val intent= intent
        var idTho = intent.getStringExtra("Idtho")
        database = Firebase.database.reference

        val usersdRef: DatabaseReference = database.child("worker")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;
                    val post = ds.child("datatemp")
                    var locc = post.getValue<UserWorkAdd>(UserWorkAdd::class.java)!!
                    if(locc.idDovat.equals(""))
                    {
                        //cardviewHC.visibility =View.VISIBLE
                    }

                    Log.d("thu1",tcmndd)
                    Log.d("thu2",idTho)
                    if(tcmndd.equals(idTho))
                    {

                        if(locc.flagCheck == true)
                        {
                            cardviewAC.visibility = View.VISIBLE
                        }
                        //idCard5.visibility = View.VISIBLE
                        Log.d("datatemp",post.toString())

                        if(locc.tinhtranghuhai.equals("HT"))
                        {
                            cardviewHoanTat.visibility = View.VISIBLE
                        }
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersdRef.addValueEventListener(eventListener)

        btnHuy.setOnClickListener {
            huyGiaoDich(idTho)
        }
    }
    fun huyGiaoDich(idtho: String)
    {
        val userWorkAdd = UserWorkAdd("","",false,false,"","",
                "","","","","","","")
        database.child("worker").child(idtho).child("datatemp").setValue(userWorkAdd)
    }

}