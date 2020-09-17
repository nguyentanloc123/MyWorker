package com.example.myworker.Screen.Danhsach

import RecyclerAdapter
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myworker.FragmentContanier.FragmentUser
import com.example.myworker.R
import com.example.myworker.UserData.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_mainthongtin.*

class Mainthongtin : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainthongtin)
        database = Firebase.database.reference
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        FragmentUser.GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        CallApi()

    }
    fun CallApi()
    {
        val itemList:MutableList<DataLoaiSuaChua> = ArrayList()
        val intent = intent
        val id = intent.getIntExtra("id",0)

        val usersdRef: DatabaseReference = database.child("vatdungsuachua")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (ds in dataSnapshot.children) {
                        var Id = ds.getValue<IdData>(IdData::class.java)!!.Id
                        Log.d("item",Id.toString())
                        if(id == Id)
                        {
                            var name = ds.key
                            val post = ds.child("dasachhuhai")
                            textView16.setText(name)
                            for (loc in post.children)
                            {
                                var postTemp = loc.getValue<DataLoaiSuaChua>(DataLoaiSuaChua::class.java)!!
                                Log.d("dasachhuhai",postTemp.toString())
                                itemList.add(postTemp)
                                recyclerView.adapter = RecyclerAdapter(itemList)
                                Log.d("item",postTemp.toString())
                            }

                        }

                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        usersdRef.addValueEventListener(eventListener)



//        val usersdRef: DatabaseReference = database.child("vatdungsuachua")
//        val eventListener: ValueEventListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (ds in dataSnapshot.children) {
////                       var Id = ds.getValue<IdData>(IdData::class.java)!!.Id
////                        Log.d("thamso",id.toString())
////                       // Toast.makeText(this,Id.toString(),Toast.LENGTH_SHORT)
////                        Log.d("thamso",Id.toString())
////                     if(id == Id)
////                        {
////                            var name = ds.key
////                            textView16.setText(name)
////                        }
//                    val post = ds.child("dasachhuhai")
//                    Log.d("dasachhuhai",post.toString())
//                    for (loc in post.children)
//                    {
//                        var postTemp = loc.getValue<DataLoaiSuaChua>(DataLoaiSuaChua::class.java)!!
//                        itemList.add(postTemp)
//                        recyclerView.adapter = RecyclerAdapter(itemList)
//                        Log.d("item",postTemp.toString())
//                    }
//
////                    var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.userName;
////                    Log.d("email",tcmndd)
////                    var tpassworkk =ds.getValue<WorkerData>(WorkerData::class.java)!!.passwork;
////                    var idTho =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;
////
////                    Log.d("email",tpassworkk)
////                    if (username.text.toString().equals(tcmndd) && tpassworkk.equals(editText.text.toString()))
////                    {
////                        cmndd = tcmndd
////                        passworkk = tpassworkk
////                        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
////                        with (sharedPref.edit()) {
////                            putString(getString(R.string.idTho), idTho)
////                            putString(getString(R.string.usernameTho), tcmndd)
////                            commit()
////                        }
////                        goMainMap()
////                    }
//
//                }
//            }
//            override fun onCancelled(databaseError: DatabaseError) {}
//        }
//        usersdRef.addListenerForSingleValueEvent(eventListener)
        //  val user = auth.currentUser
        Toast.makeText(baseContext, "Authentication success.",
                Toast.LENGTH_SHORT).show()
    }
}