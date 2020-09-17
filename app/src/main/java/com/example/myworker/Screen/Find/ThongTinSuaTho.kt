package com.example.myworker.Screen.Find

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myworker.R
import com.example.myworker.Tho.SuaXongActivity
import com.example.myworker.UserData.UserWorkAdd
import com.example.myworker.UserData.WorkerData
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
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.*
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.textView20
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.textView21
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.textView22
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.textView25
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.textView27
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.textView29
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.textView35
import kotlinx.android.synthetic.main.activity_thong_tin_sua_tho.txttongchiphi
import java.text.DecimalFormat
import java.text.NumberFormat

class ThongTinSuaTho : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var database: DatabaseReference
    private lateinit var mMap: GoogleMap
    private var lat: Double = 0.0
    private var lng: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thong_tin_sua_tho)

        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
        val userName = sharedPref.getString(getString(R.string.usernameTho), "Khách hàng")

        val sharedPref1 = getSharedPreferences("IDTHO_1", Context.MODE_PRIVATE)
        val idTho = sharedPref1.getString(getString(R.string.idTho),"123")
        database = Firebase.database.reference

        val usersdRef: DatabaseReference = database.child("worker")

       btnTimTho.setOnClickListener {
           database.child("worker").child(userName.toString()).child("datatemp").child("flagCheck").setValue(true)

           startActivity(Intent(this, SuaXongActivity::class.java))

       }


        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    var tcmndd =ds.getValue<WorkerData>(WorkerData::class.java)!!.idTho;
                    Log.d("id12",tcmndd)
                    if(tcmndd.equals(idTho))
                    {
                        val post = ds.child("datatemp")
                        var locc = post.getValue<UserWorkAdd>(UserWorkAdd::class.java)!!
                        lat = locc.latt!!.toDouble()
                        lng = locc.lngg!!.toDouble()
                        textView20.setText(locc.sodienthoai)
                        textView21.setText(locc.diachihientai)
                        textView22.setText(locc.tenSuaChua)
                        textView25.setText(locc.tinhtranghuhai)
                        textView27.setText(locc.dateDanng)
                        textView29.text= formatMoney(locc.giaTienSua!!.toLong())+ 'đ'
                        var s: Int = 13500
                        var tongchiphi = (locc.giaTienSua!!.toLong() * locc.soLanTinh!!.toLong() + s)
                        var tongchi = formatMoney(tongchiphi)
                        txttongchiphi.text = tongchi+ 'đ'
                        if(locc.loaithanhtoan == true)
                            textView35.text = "Thanh toán thẻ"
                        else if(locc.loaithanhtoan== false)
                            textView35.text ="Thanh toán tiền mặt"
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersdRef.addListenerForSingleValueEvent(eventListener)


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
    }
    fun formatMoney(myNumber: Long): String
    {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(myNumber)
        return formattedNumber
    }
}