package com.example.myworker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myworker.AdapterComment.CommentAdapter
import com.example.myworker.UserData.DataComment
import com.example.myworker.UserData.UserWorkAdd
import com.example.myworker.UserData.WorkerData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_tho_info.*
import java.text.SimpleDateFormat
import java.util.*


class ThoInfoActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var database: DatabaseReference
     private lateinit var idTho: String
    private lateinit var usernameTho:String
    private lateinit var usernameUser:String
    private var hud: KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tho_info)
        val intent = intent
        idTho= intent.getStringExtra("idTho")
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        val sharedPref = this.getSharedPreferences("", Context.MODE_PRIVATE)

        val sharedPref1 = getSharedPreferences("OBJTIMTHO", Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String = sharedPref1.getString(getString(R.string.objsuachua), "khongcogi").toString()
        val obj: UserWorkAdd = gson.fromJson(json, UserWorkAdd::class.java)
        database = Firebase.database.reference

        usernameUser = sharedPref.getString(getString(com.example.myworker.R.string.usernameUser), "loc dep trai").toString()


        loadComment()
        loadDataTho(idTho)
        btnSend.setOnClickListener {
            createComment()
        }


        btnChoose.setOnClickListener {
            database.child("worker").child(usernameTho).child("datatemp").setValue(obj)
            var intent= Intent(this,WaitingTho::class.java)
            intent.putExtra("Idtho",idTho)
            startActivity(intent)



//            val usersdRef: DatabaseReference = database.child("worker")
//            val eventListener: ValueEventListener = object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    for (ds in dataSnapshot.children) {
//                        var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;
//                        if(idTho.equals(tcmndd))
//                        {
//                            val post = ds.child("datatemp")
//                            for (loc in post.children)
//                            {
//                                var postTemp = loc.getValue<UserWorkAdd>(UserWorkAdd::class.java)!!.flagCheck
//                                if (postTemp == true)
//                                {
//                                    // hud.dismiss()
//                                }
//                            }
//                        }
//                    }
//                }
//                override fun onCancelled(databaseError: DatabaseError) {}
//            }
//            usersdRef.addListenerForSingleValueEvent(eventListener)







        }


    }
    fun createComment()
    {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val currentDateandTime: String = sdf.format(Date())
        var comment = DataComment(currentDateandTime,usernameUser.toString(),edtComment.text.toString())
        val key = database.push().key
        database.child("worker").child(usernameTho).child("binhluan").child(key.toString()).setValue(comment)
        loadComment()
    }
    fun loadComment()
    {
        val itemList:MutableList<DataComment> = ArrayList()
        itemList.clear()
        val usersdRef: DatabaseReference = database.child("worker")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;
                    if(idTho.equals(tcmndd))
                    {
                        val post = ds.child("binhluan")
                        for (loc in post.children)
                        {
                            var postTemp = loc.getValue<DataComment>(DataComment::class.java)!!

                            itemList.add(postTemp)
                            recyclerView.adapter =CommentAdapter(itemList)
                        }
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersdRef.addListenerForSingleValueEvent(eventListener)

    }


    fun loadDataTho(idTho: String)
    {
        val sharedPref2 = this.getSharedPreferences("TENSUACHUATHO",Context.MODE_PRIVATE)
        val tensuachua = sharedPref2.getString(getString(com.example.myworker.R.string.tensuachua), "loc dep trai")

        val usersdRef: DatabaseReference = database.child("worker")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;
                    Log.d("email",tcmndd)
                    if(idTho.equals(tcmndd))
                    {
                        usernameTho= ds.getValue<WorkerData>(WorkerData::class.java)!!.userName.toString();
                        txthovaten.setText(ds.getValue<WorkerData>(WorkerData::class.java)!!.fullname)
                        txtchuyenmon.setText(tensuachua)
                        txtnamkinhnghiem.setText(ds.getValue<WorkerData>(WorkerData::class.java)!!.kinhnghiem)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersdRef.addListenerForSingleValueEvent(eventListener)
    }
}