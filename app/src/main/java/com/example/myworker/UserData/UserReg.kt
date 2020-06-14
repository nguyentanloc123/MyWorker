package com.example.myworker.UserData

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserReg(
    var username: String? = "",
    var email: String? = "",
    var passwork: String? = ""
)
