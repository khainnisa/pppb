package com.example.tugaspertemuan11retrofit.network

import com.example.tugaspertemuan11retrofit.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2")
    fun getAllUsers(): Call<Users>
}