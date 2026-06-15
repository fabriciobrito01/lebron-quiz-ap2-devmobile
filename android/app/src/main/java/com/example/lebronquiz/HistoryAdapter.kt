package com.example.lebronquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lebronquiz.model.QuizResult

class HistoryAdapter(private val results: List<QuizResult>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUser: TextView = view.findViewById(R.id.tvItemUser)
        val tvScore: TextView = view.findViewById(R.id.tvItemScore)
        val tvClassification: TextView = view.findViewById(R.id.tvItemClassification)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.tvUser.text = result.userName
        holder.tvScore.text = "${result.score}/10"
        holder.tvClassification.text = result.classification
    }

    override fun getItemCount() = results.size
}
