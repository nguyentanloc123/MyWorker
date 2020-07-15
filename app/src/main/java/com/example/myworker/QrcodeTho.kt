package com.example.myworker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.google.zxing.integration.android.IntentIntegrator
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrcodeTho : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_tho)

    }

}


