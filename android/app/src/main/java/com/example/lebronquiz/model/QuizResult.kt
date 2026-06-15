package com.example.lebronquiz.model

data class QuizResult(
    val id: Int? = null,
    val userName: String,
    val score: Int,
    val totalQuestions: Int,
    val classification: String,
    val date: String? = null
)
