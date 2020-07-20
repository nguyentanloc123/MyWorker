package com.example.myworker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myworker.UserData.DataLoaiSuaChua
import com.example.myworker.UserData.IdData
import com.example.myworker.UserData.UserWorkAdd
import com.example.myworker.UserData.WorkerData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_danh_sach_tho.*
import kotlinx.android.synthetic.main.activity_mainthongtin.*

class DanhSachThoActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_tho)
        database = Firebase.database.reference
        linearLayoutManager = LinearLayoutManager(this)
        recycleviewTho.layoutManager = linearLayoutManager
        CallApi()
    }
    fun CallApi()
    {
        val itemList:MutableList<WorkerData> = ArrayList()
        val intent = intent
        val id = intent.getIntExtra("id",0)
        val sharedPref1 = getSharedPreferences("IDSUA", Context.MODE_PRIVATE)
        val usernameUser = sharedPref1.getInt(getString(R.string.idsuachua),0)
        val usersdRef: DatabaseReference = database.child("worker")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (ds in dataSnapshot.children) {
                        var Id = ds.getValue<WorkerData>(WorkerData::class.java)!!.idLoaiTHo
                        var xacthuc = ds.getValue<WorkerData>(WorkerData::class.java)!!.xacthuc
                            if(usernameUser == Id &&  xacthuc== true)
                            {
                                var postTemp = ds.getValue<WorkerData>(WorkerData::class.java)!!
                                Log.d("dasachhuhai1",postTemp.toString())
                                Log.d("dasachhuhai2",Id.toString())
                                Log.d("dasachhuhai3",xacthuc.toString())
                                Log.d("dasachhuhai4",usernameUser.toString())
                                itemList.add(postTemp)
                                recycleviewTho.adapter =ThoAdapter(itemList)
                            }






//                        Log.d("item",Id.toString())
//                        if(id == Id)
//                        {
//                            var name = ds.key
//                            val post = ds.child("dasachhuhai")
//                            textView16.setText(name)
//                            for (loc in post.children)
//                            {
//                                var postTemp = loc.getValue<WorkerData>(WorkerData::class.java)!!
//                                Log.d("dasachhuhai",postTemp.toString())
//                                itemList.add(postTemp)
//                                recyclerView.adapter = ThoAdapter(itemList)
//                                Log.d("item",postTemp.toString())
//                            }
//
//                        }

                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        usersdRef.addValueEventListener(eventListener)


        Toast.makeText(baseContext, "Authentication success.",
                Toast.LENGTH_SHORT).show()
    }
}