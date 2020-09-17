package com.example.myworker.Screen.Fix

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.R
import com.example.myworker.Screen.Find.TimThoActivity
import com.example.myworker.UserData.UserWorkAdd
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_chon_sua.*
import java.io.IOException
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


class ChonSuaActivity : AppCompatActivity() {
    public lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    private var check: Boolean= false
    private var check1: Boolean= false
    private var diachiaa: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chon_sua)
        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val currentDateandTime: String = sdf.format(Date())
        txtDateTime.text= currentDateandTime
        Log.d("datetime",currentDateandTime)
        val intent = intent
        val tenDoVat = intent.getStringExtra("tenDoVat")
        val soLan = intent.getStringExtra("soLan")
        val giatien= intent.getLongExtra("giatien",10000)
        Toast.makeText(this,giatien.toString(),Toast.LENGTH_LONG).show()

        Log.d("tenDoVat",tenDoVat)
        textView37.setText(tenDoVat)
        txtdvt1.setText(soLan)
        txtdvt2.setText(soLan)
        var ss: String = "/"
        val inputTextWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var a: String  = s.toString().plus(soLan)
                txtdvt1.text = "$s $ss $soLan"
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }

        edtsolan.addTextChangedListener(inputTextWatcher)


        getLocation()

        lnbg1.setOnClickListener {
            lnbg1.setBackgroundColor(Color.parseColor("#f3f3f3"))
            lnbg2.setBackgroundColor(Color.parseColor("#ffffff"))
            check= true
        }
        lnbg2.setOnClickListener {
            lnbg2.setBackgroundColor(Color.parseColor("#f3f3f3"))
            lnbg1.setBackgroundColor(Color.parseColor("#ffffff"))
            check= false
        }
        val sharedPref1 = getSharedPreferences("SODIENTHOAI",Context.MODE_PRIVATE)
        val sodienthoai = sharedPref1.getString(getString(R.string.usernameSdt), "0907377780")

        btnXacNhan.setOnClickListener {
            val intent = Intent(this, TimThoActivity::class.java)
            var userFix: UserWorkAdd = UserWorkAdd("0",tenDoVat,false,check,editText2.text.toString()
            ,sodienthoai,diachiaa.toString(),lat.toString(),lng.toString(),currentDateandTime,"",edtsolan.text.toString(),giatien.toString())

            intent.putExtra("userFix",userFix as Serializable)
            intent.putExtra("donvitinh",soLan)
            startActivity(intent)
        }











    }
    fun getLocation(){
     fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location ->
                Log.d("location", location.toString())
                //   tempLocation.latitude = location.latitude
                lat = location.latitude
                lng = location.longitude

                var distanceBetween = LatLng(lat, lng)
                var address = getAddress((distanceBetween))
                idDiaChi.text = address
                idDiaChi.text= getCompleteAddressString(lat,lng)
                diachiaa = getCompleteAddressString(lat,lng)
              //  Toast.makeText(this,getCompleteAddressString(lat,lng),Toast.LENGTH_LONG).show()
            }

    }
    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(this)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            // 2
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            // 3
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }
        Log.e("location", addressText)
        return addressText
    }
    private fun getCompleteAddressString(LATITUDE: Double, LONGITUDE: Double): String? {
        var strAdd = ""
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
            if (addresses != null) {
                val returnedAddress = addresses[0]
                val strReturnedAddress = StringBuilder("")
                for (i in 0..returnedAddress.maxAddressLineIndex) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                }
                strAdd = strReturnedAddress.toString()
                //Log.d("MyCurrent loction address", strReturnedAddress.toString())
            } else {
              //  Log.w("My Current loction address", "No Address returned!")
            }
        } catch (e: Exception) {
            e.printStackTrace()
           // Log.w("My Current loction address", "Canont get Address!")
        }
        return strAdd
    }

}