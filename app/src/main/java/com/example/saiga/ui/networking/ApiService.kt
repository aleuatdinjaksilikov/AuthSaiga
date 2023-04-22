package com.example.saiga.ui.networking

import com.example.saiga.ui.fragments.User
import com.example.saiga.ui.models.LoginRequestData
import com.example.saiga.ui.models.SignUpRequestData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/api/auth/sign-in")
    suspend fun authLogin(@Body loginRequestData: LoginRequestData):Response<User>

    @POST("/api/auth/sign-up")
    suspend fun createUser(@Body signUpRequestData: SignUpRequestData): Response<User>

}