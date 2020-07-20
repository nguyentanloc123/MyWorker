package com.example.myworker.UserData

import com.google.firebase.database.Exclude

data class WorkerData(
    var idTho: String?="",
    var userName:String?="",
    var passwork: String?="",
    var gmail:String?="",
    var sdtThoSua: String?="" ,
    var idLoaiTHo: Int= 0,
    var fullname: String= "",
    var kinhnghiem: String= "",
    var xacthuc: Boolean= false,
    var ketnoi: Boolean = false
){

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
                "idTho" to idTho,
                "userName" to userName,
                "passwork" to passwork,
                "gmail" to gmail,
                "sdtThoSua" to sdtThoSua,
                "idLoaiTHo" to idLoaiTHo

        )
    }
}
