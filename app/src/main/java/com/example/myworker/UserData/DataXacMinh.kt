package com.example.myworker.UserData

data class DataXacMinh(
        var idTho: String,
        var ngheDangKi: Boolean= false,
        var anhChanDung: Int,
        var anhCMND: Int,
        var hopDongCaNhan: Boolean,
        var banLyLich: Boolean,
        var banPhoToCMND: Boolean,
        var banPhoToHoKhau: Boolean,
        var banPhoToBangCap: Boolean
)