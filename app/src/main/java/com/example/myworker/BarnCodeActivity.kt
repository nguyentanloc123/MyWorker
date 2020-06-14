package com.example.myworker


import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_barn_code.*

class BarnCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barn_code)
            genCode()
        countDowwn()
//        object : CountDownTimer(15000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                tvCoundDown.setText(""+millisUntilFinished / 1000)
//            }
//            override fun onFinish() {
//
//                //tvCoundDown.setText("done!")
//            }
//        }.start()

    }
    fun countDowwn()
    {
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvCoundDown.setText(""+millisUntilFinished / 1000)
            }
            override fun onFinish() {
                countDowwn()
                genCode()

        }
        }.start()
    }
    fun genCode()
    {
        try {
            var rnds = (0..10000).random()
            val barcodeEncoder = BarcodeEncoder()
            val bitmap =
                barcodeEncoder.encodeBitmap(rnds.toString(), BarcodeFormat.QR_CODE, 600, 600)
            // val imageViewQrCode: ImageView = findViewById(R.id.) as ImageView
            imgBarnCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
        }
    }



}
