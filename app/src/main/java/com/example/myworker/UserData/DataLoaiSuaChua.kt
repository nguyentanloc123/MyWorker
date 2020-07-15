package com.example.myworker.UserData

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class DataLoaiSuaChua (
   // var imageChiTietSuaChua: Int,
    var tenLoaiSuaChua: String?="",
    var donViTinh: String?="",
    var giaTien:Long?= 100000
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
                "tenLoaiSuaChua" to tenLoaiSuaChua,
                "donViTinh" to donViTinh,
                "giaTien" to giaTien
        )
    }
}