package com.example.lebronquiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lebronquiz.model.Question
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator

class QuizFragment : Fragment() {

    private var userName: String? = null
    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var tvQuestion: TextView
    private lateinit var tvQuestionCount: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbOption1: RadioButton
    private lateinit var rbOption2: RadioButton
    private lateinit var rbOption3: RadioButton
    private lateinit var rbOption4: RadioButton
    private lateinit var btnNext: MaterialButton
    private lateinit var progressBar: LinearProgressIndicator

    private val questions = listOf(
        Question(1, "Em qual ano LeBron James foi draftado?", listOf("2001", "2002", "2003", "2004"), 2),
        Question(2, "Quantos títulos da NBA LeBron possui?", listOf("2", "3", "4", "5"), 2),
        Question(3, "Qual foi o primeiro time de LeBron na NBA?", listOf("Miami Heat", "Cleveland Cavaliers", "LA Lakers", "Chicago Bulls"), 1),
        Question(4, "Em qual faculdade LeBron James jogou?", listOf("Ohio State", "Duke", "Kentucky", "Nenhuma"), 3),
        Question(5, "Quantas vezes LeBron foi MVP da temporada regular?", listOf("2", "3", "4", "5"), 2),
        Question(6, "Qual é o apelido mais famoso de LeBron James?", listOf("The Process", "King James", "Black Mamba", "The Answer"), 1),
        Question(7, "Por qual time LeBron conquistou seu primeiro título?", listOf("Cavs", "Heat", "Lakers", "Warriors"), 1),
        Question(8, "Em 2016, contra quem LeBron virou a série final de 3-1?", listOf("Spurs", "Celtics", "Warriors", "Thunder"), 2),
        Question(9, "Qual recorde histórico LeBron quebrou em 2023?", listOf("Mais assistências", "Mais rebotes", "Maior pontuador", "Mais títulos"), 2),
        Question(10, "Qual é o número que LeBron mais usou na carreira?", listOf("6", "23", "32", "8"), 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = it.getString(ARG_USER_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        tvQuestion = view.findViewById(R.id.tvQuestion)
        tvQuestionCount = view.findViewById(R.id.tvQuestionCount)
        rgOptions = view.findViewById(R.id.rgOptions)
        rbOption1 = view.findViewById(R.id.rbOption1)
        rbOption2 = view.findViewById(R.id.rbOption2)
        rbOption3 = view.findViewById(R.id.rbOption3)
        rbOption4 = view.findViewById(R.id.rbOption4)
        btnNext = view.findViewById(R.id.btnNext)
        progressBar = view.findViewById(R.id.progressBar)

        progressBar.max = questions.size
        displayQuestion()

        btnNext.setOnClickListener {
            checkAnswer()
        }

        return view
    }

    private fun displayQuestion() {
        val question = questions[currentQuestionIndex]
        tvQuestion.text = question.text
        tvQuestionCount.text = "Pergunta ${currentQuestionIndex + 1} de ${questions.size}"
        rbOption1.text = question.options[0]
        rbOption2.text = question.options[1]
        rbOption3.text = question.options[2]
        rbOption4.text = question.options[3]
        rgOptions.clearCheck()
        progressBar.setProgress(currentQuestionIndex + 1, true)
        
        if (currentQuestionIndex == questions.size - 1) {
            btnNext.text = "Finalizar"
        }
    }

    private fun checkAnswer() {
        val selectedId = rgOptions.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, "Selecione uma opção", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedRadioButton = view?.findViewById<RadioButton>(selectedId)
        val selectedIndex = rgOptions.indexOfChild(selectedRadioButton)

        if (selectedIndex == questions[currentQuestionIndex].correctAnswerIndex) {
            score++
        }

        currentQuestionIndex++

        if (currentQuestionIndex < questions.size) {
            displayQuestion()
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        val intent = Intent(activity, ResultActivity::class.java)
        intent.putExtra("USER_NAME", userName)
        intent.putExtra("SCORE", score)
        intent.putExtra("TOTAL", questions.size)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        private const val ARG_USER_NAME = "user_name"

        @JvmStatic
        fun newInstance(userName: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_NAME, userName)
                }
            }
    }
}
