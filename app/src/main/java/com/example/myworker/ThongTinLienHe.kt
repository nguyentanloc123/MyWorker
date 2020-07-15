package com.example.myworker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.UserData.UserWorkAdd
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_thong_tin_lien_he.*


class ThongTinLienHe : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    var message: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thong_tin_lien_he)
        var bundle :Bundle ?=intent.extras
         message = bundle!!.getString("value") // 1
        Log.d("value",message)
        database = Firebase.database.reference
        LoadDataFromDatabase()
        btnCall.setOnClickListener{
            Toast.makeText(this,"Xin đợi trong giây lát đang liên hệ với người đăng", Toast.LENGTH_LONG).show()
            if(!txtSdt.text.isNullOrEmpty()){
                Log.d("sdt",txtSdt.text.toString())
                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", txtSdt.text.toString(), null))
                startActivity(intent)
            }
        }
    }

    fun LoadDataFromDatabase()
    {
        val usersdRef: DatabaseReference = database.child("users")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for (ds in dataSnapshot.children) {
                        val post =ds.child("thongtindodung").getValue<UserWorkAdd>(
                            UserWorkAdd::class.java)!!
                       if(post.latt.toString().equals(message))
                       {
                           //txtnameKhachhang.setText("Tên khách hàng: "+ post.realname)
                         //  txtThietbi.setText("Thiết bị: "+ post.dovatcansua)
                           txtTinhtrang.setText("Tình trạng: "+ post.tinhtranghuhai)
                           txtVitri.setText("Địa chỉ liên hệ: "+ post.diachihientai)
                           txtSdt.setText("Số điện thoại: "+ post.sodienthoai)


                       }
                    }

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        usersdRef.addValueEventListener(eventListener)

    }

}
