package com.example.myworker.FragmentContanier

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog
import com.example.myworker.MethodLoginAnReg
import com.example.myworker.R

class FragmentSetting : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.settingfragment, container, false)
        val logouttxt = rootView.findViewById<TextView>(R.id.txtLogout)
        val sharedPref = this.requireContext().getSharedPreferences("", Context.MODE_PRIVATE)
        logouttxt.setOnClickListener{
            TTFancyGifDialog.Builder(this.requireActivity())
                .setTitle("Đăng Xuất")
                .setMessage("Bạn có muốn đăng xuất ?")
                .setPositiveBtnText("Ok")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Cancel")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.tks) //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked {
                    Toast.makeText(this.requireActivity(), "Ok", Toast.LENGTH_SHORT).show()
                    with (sharedPref.edit()) {
                        sharedPref.edit().clear()
                        commit()
                    }
                    startActivity(Intent(this.requireActivity(), MethodLoginAnReg::class.java))

                }
                .OnNegativeClicked {

                }
                .build()
        }


        return  rootView
    }
}