package com.example.first_app

import com.google.firebase.database.Exclude

data class Model(
    val title : String = "",
    val body : String = "",
    val uid : String = "",
    val time : String = "",
   val email : String = ""
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "title" to title,
            "body" to body,
            "uid" to uid,
            "time" to time,
            "email" to email
        )
    }
}
