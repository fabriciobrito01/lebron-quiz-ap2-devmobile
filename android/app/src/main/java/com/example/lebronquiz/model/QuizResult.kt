package com.example.lebronquiz.model

import com.google.gson.annotations.SerializedName

data class QuizResult(
    val id: Int? = null,
    
    @SerializedName("nome")
    val userName: String,
    
    @SerializedName("acertos")
    val score: Int,
    
    @SerializedName("nivel")
    val classification: String,
    
    @SerializedName("data")
    val date: String = "2024-06-15" // Data padrão para o backend
)
