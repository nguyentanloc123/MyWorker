package com.example.myworker.Screen.Map

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.myworker.FragmentContanier.FragmentAccount
import com.example.myworker.FragmentContanier.FragmentMain
import com.example.myworker.FragmentContanier.FragmentSetting
import com.example.myworker.FragmentContanier.FragmentUser
import com.example.myworker.R
import kotlinx.android.synthetic.main.activity_main_map.*

class MainMap : AppCompatActivity() {
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_map)
        setUpMap()
        if (savedInstanceState == null) {
            val fragment = FragmentMain()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }
        bottom_navi.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.navigation_main -> {
                    title=resources.getString(R.string.app_name)
                    loadFragment(FragmentMain())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_cash -> {
                    title=resources.getString(R.string.app_name)
                    loadFragment(FragmentAccount())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_user -> {
                    title=resources.getString(R.string.app_name)
                    loadFragment(FragmentUser())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    title=resources.getString(R.string.app_name)
                    loadFragment(FragmentSetting())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false

        }

    }
    private fun loadFragment(fragment: Fragment) {
        // load fragment

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
    }
}