package com.example.myworker

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myworker.UserData.UserWorkAdd
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_do_vat_sua.*
import java.text.SimpleDateFormat
import java.util.*


class AddDoVatSua : AppCompatActivity() {
    private lateinit  var iddocansua: String
    private lateinit var database: DatabaseReference
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double? = 0.0
    private var lng: Double? = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_do_vat_sua)


       // val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        //val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
     //   val output: String = formatter.format(parser.parse(Locale.getDefault().toString())

        //mTimeText.setText("Time: " + dateFormat.format(date))
   //     Log.d("ngaythang",currentDate.toString())
    //    Log.d("ngaythang",currentTime.toString())
        database = Firebase.database.reference
        val docansua = intent.getStringExtra("tendovat")
         iddocansua= intent.getStringExtra("idDoVat")

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
        val userNameLogin = sharedPref.getString(getString(R.string.usernameUser), "")
        edtDoCanSua.setText(docansua)
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                    location: Location? ->
                Log.d("location1",location.toString())
                lat = location?.latitude
                lng = location?.longitude

            }

        btnSend.setOnClickListener {
            SaveData();
         //   val thongtinvitri = UserWorkAddLocation(lat.toString(),lng.toString())
       //     database.child("user").child(userNameLogin.toString()).child("thongtinvitri").setValue(thongtinvitri)

            Toast.makeText(this,"Lưu dữ liệu thành công",Toast.LENGTH_LONG).show();
        }
    }
    fun SaveData()
    {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val key = database.child("thongtindodung").push().key
        val sharedPref = getSharedPreferences("", Context.MODE_PRIVATE)
        val userNameLogin = sharedPref.getString(getString(R.string.usernameUser), "")
        val keyID = sharedPref.getString(getString(R.string.keyID),"")
        val thogntinsua = UserWorkAdd(iddocansua,false,editName.text?.toString(),
            edtDoCanSua.text?.toString(),
            edtTinhTrang.text?.toString(),
            edtSDT.text?.toString(),
        edtVitriHienTai.text?.toString(),
        lat.toString(), lng.toString(), currentDate+" "+currentTime,"")
        with (sharedPref.edit()) {
            putString(getString(R.string.keyID),key.toString())
            commit()
        }


        database.child("users").child(userNameLogin.toString()).child("thongtindodung").child(key.toString()).setValue(thogntinsua)
    }




}

