package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {



    @POST("api/sendCode")
    fun sendCode(@Header("email")email:String):Call<SendCode>

    @POST("api/signin")
    fun signIn(
        @Header("email")email: String,
        @Header("code") code:String
    ):Call<SignIn>

     /*  @POST("api/createUser")
        suspend fun createUser(
           "Authorization" token: String,
        @Body createUserRequest: CreateUserRequest
        ): Response<CreateUserResponse>*/
}