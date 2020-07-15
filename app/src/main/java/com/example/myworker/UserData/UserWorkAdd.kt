package com.example.myworker.UserData

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class UserWorkAdd(
        var idDovat: String? = "",
        var tenSuaChua:String? ="",
        var flagCheck: Boolean? = false,
        var loaithanhtoan: Boolean? =false,
        var tinhtranghuhai: String? = "",
        var sodienthoai: String? = "",
        var diachihientai: String? = "",
        var latt: String? = "",
        var lngg: String? = "",
        var dateDanng: String? = null,
        var dateSua: String? = null,
        var soLanTinh: String?  = "",
        var giaTienSua: String? =""

) :Serializable