package com.example.lebronquiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val userName = intent.getStringExtra("USER_NAME") ?: "Anônimo"

        if (savedInstanceState == null) {
            val fragment = QuizFragment.newInstance(userName)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
