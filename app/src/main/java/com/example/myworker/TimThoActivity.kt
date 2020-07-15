package com.example.myworker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.UserData.UserWorkAdd
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.text.DecimalFormat
import java.text.NumberFormat


class TimThoActivity : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tim_tho)

        val intent =intent
        val loc = intent.extras?.get("userFix") as UserWorkAdd

        Log.d("locdeptrai",loc.toString())
        loc.giaTienSua = "123123"

        Log.d("locdeptrai",loc.toString())


//        val mapFragment =
//            supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
//        mapFragment!!.getMapAsync(this)
//            Log.d("loca","adasdadasda")
//        mapFragment?.getMapAsync { p0 ->
//            if (p0 != null) {
//                mMap = p0
//
//            }
//        }

    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            mMap = p0
        }
        val Kuta = LatLng(12.0, 28.0)
        mMap.addMarker(MarkerOptions().position(Kuta).title("kk"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Kuta))
    }
    fun formatMoney()
    {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber = 1000000
        val formattedNumber: String = formatter.format(myNumber)
    }
}