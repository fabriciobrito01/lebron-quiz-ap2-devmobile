package com.example.lebronquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lebronquiz.model.QuizResult
import com.example.lebronquiz.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra("USER_NAME") ?: "Anônimo"
        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 5)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvClassification = findViewById<TextView>(R.id.tvClassification)
        val btnBackHome = findViewById<Button>(R.id.btnBackHome)

        tvScore.text = getString(R.string.score_label, score, total)

        val classification = when {
            score == total -> "Rei do Basquete"
            score >= total * 0.8 -> "Especialista"
            score >= total * 0.6 -> "Superfã"
            score >= total * 0.4 -> "Fã Casual"
            else -> "Novato"
        }

        tvClassification.text = getString(R.string.classification_label, classification)

        saveResultToApi(userName, score, total, classification)

        btnBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun saveResultToApi(userName: String, score: Int, total: Int, classification: String) {
        val result = QuizResult(
            userName = userName,
            score = score,
            classification = classification
        )

        RetrofitClient.instance.saveResult(result).enqueue(object : Callback<QuizResult> {
            override fun onResponse(call: Call<QuizResult>, response: Response<QuizResult>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ResultActivity, "Resultado salvo!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ResultActivity, "Erro ao salvar resultado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuizResult>, t: Throwable) {
                Toast.makeText(this@ResultActivity, "Falha na conexão: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
