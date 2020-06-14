package com.example.myworker.UserData

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties



data class User(
    var username: String? = "",
    var email: String? = "",
    var password: String? = "",
    var lienket: Boolean? = false
){

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "username" to username,
            "email" to email,
            "password" to password

        )
    }
}

