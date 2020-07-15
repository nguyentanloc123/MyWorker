package com.example.myworker.UserData

import com.google.firebase.database.Exclude

data class IdData(
        var Id: Int= 0
)
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
                "Id" to Id
        )
    }
}