package com.example.myworker

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main_tho.*


class MainTho : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // reg noti
        setContentView(R.layout.activity_main_tho)
        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
        val idTho = sharedPref.getString(getString(R.string.idTho), "Khách hàng")
        val userName = sharedPref.getString(getString(R.string.usernameTho), "Khách hàng")
        txtUserTho.setText(userName)
//        val emptyIntent = Intent()
//        val pendingIntent = PendingIntent.getActivity(this,1000, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
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
               startActivity(Intent(this,XacMinhThoActivity::class.java))
           }





        }
        // Create an explicit intent for an Activity in your app


    }




}