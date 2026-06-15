package com.example.lebronquiz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lebronquiz.model.QuizResult
import com.example.lebronquiz.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val rvHistory = findViewById<RecyclerView>(R.id.rvHistory)
        rvHistory.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getHistory().enqueue(object : Callback<List<QuizResult>> {
            override fun onResponse(call: Call<List<QuizResult>>, response: Response<List<QuizResult>>) {
                if (response.isSuccessful) {
                    val history = response.body() ?: emptyList()
                    rvHistory.adapter = HistoryAdapter(history)
                } else {
                    Toast.makeText(this@HistoryActivity, "Erro ao carregar histórico", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<QuizResult>>, t: Throwable) {
                Toast.makeText(this@HistoryActivity, "Falha na conexão: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
