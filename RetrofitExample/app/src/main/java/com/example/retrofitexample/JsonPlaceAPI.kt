package com.example.retrofitexample


import com.google.gson.annotations.SerializedName

 class JsonPlaceAPI(

    @SerializedName("email")
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String
)