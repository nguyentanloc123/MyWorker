package com.example.myworker.Tho

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog
import com.example.myworker.Screen.Login.LoginWorkerActivity
import com.example.myworker.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val sharedPref = this.getSharedPreferences("", Context.MODE_PRIVATE)

        txtLogout.setOnClickListener {
            TTFancyGifDialog.Builder(this)
                .setTitle("Đăng Xuất")
                .setMessage("Bạn có muốn đăng xuất ?")
                .setPositiveBtnText("Ok")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Cancel")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.tks) //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked {
                    Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
                    with (sharedPref.edit()) {
                        sharedPref.edit().clear()
                        commit()
                    }
                    startActivity(Intent(this, LoginWorkerActivity::class.java))

                }
                .OnNegativeClicked {

                }
                .build()
        }


    }
}