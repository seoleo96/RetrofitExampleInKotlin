package com.example.retrofitexample

import retrofit2.Call
import retrofit2.http.GET




// https://jsonplaceholder.typicode.com/      users

interface JSONPlaceholderAPI {
    @GET("users")
    fun getUsers(): Call<List<JsonPlaceAPI>>
}