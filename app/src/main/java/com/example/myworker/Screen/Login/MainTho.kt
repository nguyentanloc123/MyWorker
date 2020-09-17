package com.example.myworker.Screen.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.R
import com.example.myworker.Screen.Find.ThongTinSuaTho
import com.example.myworker.Screen.OTP.XacMinhThoActivity
import com.example.myworker.Tho.SettingActivity
import com.example.myworker.UserData.UserWorkAdd
import com.example.myworker.UserData.WorkerData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main_tho.*


class MainTho : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // reg noti
        setContentView(R.layout.activity_main_tho)
        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
        val sharedPref1 = getSharedPreferences("IDTHO_1", Context.MODE_PRIVATE)
        val idTho = sharedPref1.getString(getString(R.string.idTho),"123")
        val userName = sharedPref.getString(getString(R.string.usernameTho), "Khách hàng")
        txtUserTho.setText(userName)
        database = Firebase.database.reference
        Log.d("id13",idTho)
        val usersdRef: DatabaseReference = database.child("worker")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;
                    Log.d("id12",tcmndd)
                    if(tcmndd.equals(idTho))
                    {
                        val post = ds.child("datatemp")
                        var locc = post.getValue<UserWorkAdd>(UserWorkAdd::class.java)!!
                        if (locc.tinhtranghuhai.equals(""))
                        {
                            idCard5.visibility= View.GONE
                        }
                        if (!locc.tinhtranghuhai.isNullOrEmpty())
                        {
                            idCard5.visibility= View.VISIBLE
                        }
                        Log.d("datatemp",post.toString())
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersdRef.addValueEventListener(eventListener)

        btnDongY.setOnClickListener {
            startActivity(Intent(this, ThongTinSuaTho::class.java))

        }
        btnHuy.setOnClickListener {
            val userWorkAdd = UserWorkAdd("","",false,false,"","",
                    "","","","","","","")
            database.child("worker").child(userName.toString()).child("datatemp").setValue(userWorkAdd)
        }

        caidat.setOnClickListener {
            startActivity(Intent(this,SettingActivity::class.java))
        }


        btnXacMinh.setOnClickListener {
//            val mBuilder = NotificationCompat.Builder(this)
//                    .setSmallIcon(R.drawable.diemthuong_icon)
//                    .setContentTitle("My notification")
//                    .setContentText("Hello World!")
//                    .setContentIntent(pendingIntent) //Required on Gingerbread and below
//
//            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.notify(1000, mBuilder.build())
            if(txtXacMinh.text.equals("Chưa xác minh"))
            {
                startActivity(Intent(this,
                    XacMinhThoActivity::class.java))
            }





        }
        // Create an explicit intent for an Activity in your app


    }
    fun ShowToast()
    {
        Toast.makeText(this,"chap nhan",Toast.LENGTH_SHORT).show()
    }




}