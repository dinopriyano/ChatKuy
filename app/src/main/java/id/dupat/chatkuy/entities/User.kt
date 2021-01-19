package id.dupat.chatkuy.entities

import com.google.firebase.Timestamp

data class User(
        val created_date: Timestamp? = Timestamp.now(),
        val email: String = "",
        val fullname: String = "",
        var id: String = "",
        val image_url: String = "",
        val password: String = "",
        val status: String = "",
        val username: String = ""
)
