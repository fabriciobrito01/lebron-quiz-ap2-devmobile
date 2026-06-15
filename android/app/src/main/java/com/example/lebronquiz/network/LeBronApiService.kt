package com.example.lebronquiz.network

import com.example.lebronquiz.model.QuizResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LeBronApiService {
    
    // Alinhado com o endpoint @app.post("/resultados") do FastAPI
    @POST("resultados")
    fun saveResult(@Body result: QuizResult): Call<QuizResult>

    // Alinhado com o endpoint @app.get("/resultados") do FastAPI
    @GET("resultados")
    fun getHistory(): Call<List<QuizResult>>
}
