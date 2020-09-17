package com.example.myworker.Screen.Find

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.R
import com.example.myworker.Screen.Danhsach.DanhSachThoActivity
import com.example.myworker.UserData.User
import com.example.myworker.UserData.UserWorkAdd
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_tim_tho.*
import java.text.DecimalFormat
import java.text.NumberFormat


class TimThoActivity : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tim_tho)
        database = Firebase.database.reference

        val intent =intent
        val loc = intent.extras?.get("userFix") as UserWorkAdd
        val donvitinh=intent.getStringExtra("donvitinh")
        Log.d("locdeptrai",loc.toString())
        lat = loc.latt!!.toDouble()
        lng = loc.lngg!!.toDouble()

        val sharedPref1 = getSharedPreferences("OBJTIMTHO", Context.MODE_PRIVATE)

        val gson = Gson()
        val json = gson.toJson(loc)
        with (sharedPref1.edit())
        {
            putString(getString(R.string.objsuachua), json)
            commit()
        }



        Log.d("locdeptrai",loc.toString())

        btnTimTho.setOnClickListener {
            startActivity(Intent(this,
                DanhSachThoActivity::class.java))
        }


        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
        val usernameUser = sharedPref.getString(getString(R.string.usernameUser), "loc dep trai")
        val usersdRef: DatabaseReference = database.child("users")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val username =ds.getValue<User>(User::class.java)!!.username;
                    //  val temp = edtemail.text.toString()
                    if(username.toString().equals(usernameUser))
                    {
                        textView20.text =   ds.getValue<User>(User::class.java)!!.sodienthoai

                        //Toast.makeText(get, "load", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersdRef.addListenerForSingleValueEvent(eventListener)






        textView19.text =usernameUser
        textView23.setText(loc.soLanTinh+ '/' + donvitinh)
        textView21.text = loc.diachihientai
        textView25.setText(loc.tinhtranghuhai)
        textView27.text = loc.dateDanng
        if(loc.loaithanhtoan == true)
            textView35.text = "Thanh toán thẻ"
        else if(loc.loaithanhtoan== false)
            textView35.text ="Thanh toán tiền mặt"
        textView29.text= formatMoney(loc.giaTienSua!!.toLong())+ 'đ'
        var s: Int = 13500
        var tongchiphi = (loc.giaTienSua!!.toLong() * loc.soLanTinh!!.toLong() + s)
        var tongchi = formatMoney(tongchiphi)
        txttongchiphi.text = tongchi+ 'đ'

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
            Log.d("loca","adasdadasda")
        mapFragment?.getMapAsync { p0 ->
            if (p0 != null) {
                mMap = p0

            }
        }

    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            mMap = p0
        }
        val Kuta = LatLng(lat, lng)
        mMap.addMarker(MarkerOptions().position(Kuta).title("kk"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kuta, 16.0f))

      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(Kuta,16.0f))
    }
    fun formatMoney(myNumber: Long): String
    {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(myNumber)
        return formattedNumber
    }
}