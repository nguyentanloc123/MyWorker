package com.example.myworker.UserData

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class UserWorkAdd(
    var idDovat: String? = "",
    var flagCheck: Boolean? = false,
    var realname: String? = "",
    var dovatcansua: String? = "",
    var tinhtranghuhai: String? = "",
    var sodienthoai: String? = "",
    var diachihientai: String? = "",
    var latt : String? = "",
    var lngg : String? = "",
    var dateDanng: String? = null,
    var dateSua: String? = null
)