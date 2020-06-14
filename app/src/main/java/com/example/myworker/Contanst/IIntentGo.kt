package com.example.myworker.Contanst

import android.app.Activity
import android.content.Context
import android.content.Intent

class IIntentGo {
    fun goAnotherIntent( context: Context, mcontext: Context)
    {

        context.startActivity(Intent(context,mcontext.javaClass))
    }
}