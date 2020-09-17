package com.example.myworker.FragmentContanier
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.myworker.Screen.Danhsach.Mainthongtin
import com.example.myworker.R
import com.example.myworker.Screen.Danhsach.ThongTinLienHe
import com.example.myworker.UserData.User
import com.example.myworker.UserData.UserWorkAdd
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.IOException
import java.text.DecimalFormat


class FragmentMain : Fragment(), GoogleMap.OnMarkerClickListener {
    private lateinit var database: DatabaseReference
    private lateinit var mMap: GoogleMap
    private var thongtin: List<UserWorkAdd> = ArrayList()

    private val lngMutableLiveData = MutableLiveData<Double>()
    private val latlngs: ArrayList<LatLng> = ArrayList()
    private val temptest: ArrayList<Double> = ArrayList()
    private val options = MarkerOptions()
    private var user = UserWorkAdd()
    // var latlngs: Array<LatLng> =
    val list = listOf<UserWorkAdd>()
    var countDownTime: Int = 0
    private lateinit var distanceBetween: LatLng
    private val mSupportMapFragment: SupportMapFragment? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double = 0.0
    private var lng: Double = 0.0

    //private var temp :LatLng = LatLng(null,nu)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.homeframent,container,false)
        //  fusedLocationClient = LocationServices.getFusedLocationProviderClient()

        val rootView = inflater.inflate(R.layout.homeframent, container, false)
        val sharedPref = this.requireActivity().getSharedPreferences("", Context.MODE_PRIVATE)
        val usernameUser = sharedPref.getString(getString(com.example.myworker.R.string.usernameUser), "loc dep trai")
        database = Firebase.database.reference

        Log.d("ttt3", usernameUser)
       // Log.d("ttt4", usersdt)
        Toast.makeText(this.requireContext(), usernameUser,Toast.LENGTH_LONG).show()


        val btnOngNuoc = rootView.findViewById<ImageView>(R.id.btnOngNuoc)
        val btnODien = rootView.findViewById<ImageView>(R.id.btnODien)
        val userName = rootView.findViewById<TextView>(R.id.txtUserTho)
        userName.setText(usernameUser)
        // load data

        val usersdRef: DatabaseReference = database.child("users")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val username =ds.getValue<User>(User::class.java)!!.username;
                    //  val temp = edtemail.text.toString()
                    if(username.toString().equals(usernameUser))
                    {
                        Log.d("ttt3", ds.getValue<User>(User::class.java)!!.sodienthoai)
                        //Toast.makeText(get, "load", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersdRef.addListenerForSingleValueEvent(eventListener)







        val sharedPref1 = this.requireActivity().getSharedPreferences("IDSUA",Context.MODE_PRIVATE)
        val sharedPref2 = this.requireActivity().getSharedPreferences("TENSUACHUATHO",Context.MODE_PRIVATE)

        btnOngNuoc.setOnClickListener {
            Toast.makeText(this.requireActivity(),"click",Toast.LENGTH_LONG).show()
            val intent = Intent(this.requireActivity(), Mainthongtin::class.java)
            with (sharedPref1.edit())
            {
                putInt(getString(com.example.myworker.R.string.idsuachua),0)
                commit()
            }
            with (sharedPref2.edit())
            {
                putString(getString(com.example.myworker.R.string.tensuachua),"Thợ sửa nước")
                commit()
            }



            intent.putExtra("id",0)
            startActivity(intent)

        }
        btnODien.setOnClickListener {
            Toast.makeText(this.requireActivity(),"click",Toast.LENGTH_LONG).show()
            with (sharedPref1.edit())
            {
                putInt(getString(com.example.myworker.R.string.idsuachua),1)
                commit()
            }
            with (sharedPref2.edit())
            {
                putString(getString(com.example.myworker.R.string.tensuachua),"Thợ sửa điện")
                commit()
            }

            val intent = Intent(this.requireActivity(), Mainthongtin::class.java)
            intent.putExtra("id",1)
            startActivity(intent)

        }
        database = Firebase.database.reference




//        fusedLocationClient =
//            LocationServices.getFusedLocationProviderClient(this.requireActivity())
//        val mapFragment =
//            childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
//         Log.d("loca","adasdadasda")
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location: Location ->
//                Log.d("location", location.toString())
//                //   tempLocation.latitude = location.latitude
//                lat = location.latitude
//                lng = location.longitude
//                distanceBetween = LatLng(lat, lng)
//
//            }
//        mapFragment?.getMapAsync { p0 ->
//            if (p0 != null) {
//                mMap = p0
//                val usersdRef: DatabaseReference = database.child("users")
//                val eventListener: ValueEventListener = object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            for (ds in dataSnapshot.children) {
//                                val post = ds.child("thongtindodung")
//                                for (loc in post.children)
//                                {
//                                    var postTemp = loc.getValue<UserWorkAdd>(UserWorkAdd::class.java)!!
//                                    Log.d("loc", postTemp.toString())
//                                    addd(postTemp)
//
//                                }
//                            }
//                            countDownTime++
//
////                            lng =ds.child("thongtindodung").getValue<UserWorkAdd>(UserWorkAdd::class.java)!!.lngg!!.toDouble()
////                            Log.d("loc",lat.toString()+ " "+ lng.toString())
////                            lngMutableLiveData.postValue(lat)
//                        }
//                    }
//
//                    override fun onCancelled(databaseError: DatabaseError) {
//                    }
//                }
//                usersdRef.addValueEventListener(eventListener)
//
//                Log.d("loc", "123")
//                mMap.setOnMarkerClickListener(this)
//
//
//            }
//        }

        return rootView;
    }

    fun addd(temp: UserWorkAdd) {
        var sss = temp
        // list.toMutableList().add(countDownTime,temp)
        thongtin.toMutableList().add(temp)
        thongtin.toMutableList().add(sss)

        var myplace = LatLng(temp.latt?.toDouble()!!, temp.lngg?.toDouble()!!)

        var text = getAddress(myplace)
        //var texttemp.setTag = UserWorkAdd(temp.realname,temp.dovatcansua,temp.tinhtranghuhai,temp.sodienthoai,temp.diachihientai)
        if (temp.flagCheck == false) {
            latlngs.add(myplace)

            CalculationByDistance(myplace, distanceBetween)

            mMap.addMarker(MarkerOptions().position(myplace).title(temp.diachihientai))
                .showInfoWindow()
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myplace, 40.0f))


    }

    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(this.requireContext())
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
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(
                        i
                    )
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }
        return addressText
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        val valuee: String = p0!!.position.latitude.toString()
        val intent = Intent(this.requireActivity(), ThongTinLienHe::class.java)
        intent.putExtra("value", valuee)
        startActivity(intent)
        Log.d("loc", "click")
        return false
    }

    fun CalculationByDistance(StartP: LatLng, EndP: LatLng): Double {
        val Radius = 6371 // radius of earth in Km
        val lat1 = StartP.latitude
        val lat2 = EndP.latitude
        val lon1 = StartP.longitude
        val lon2 = EndP.longitude
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + (Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2)))
        val c = 2 * Math.asin(Math.sqrt(a))
        val valueResult = Radius * c
        val km = valueResult / 1
        val newFormat = DecimalFormat("####")
        val kmInDec: Int = Integer.valueOf(newFormat.format(km))
        val meter = valueResult % 1000
        val meterInDec: Int = Integer.valueOf(newFormat.format(meter))
        Log.i(
            "Radius Value", "" + valueResult + "   KM  " + kmInDec
                    + " Meter   " + meterInDec
        )
        return Radius * c
    }
}





//    override fun onMapReady(googleMap: GoogleMap) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        mMap = googleMap
//
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//    }




