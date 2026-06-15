package com.example.lebronquiz.network

import com.example.lebronquiz.model.QuizResult
import com.example.lebronquiz.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LeBronApiService {
    @POST("users")
    fun registerUser(@Body user: User): Call<User>

    @POST("results")
    fun saveResult(@Body result: QuizResult): Call<QuizResult>

    @GET("results")
    fun getHistory(): Call<List<QuizResult>>
}
