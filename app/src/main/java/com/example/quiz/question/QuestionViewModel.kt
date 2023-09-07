package com.example.quiz.question


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.quiz.Question


class QuestionViewModel : ViewModel() {


    private val questions: ArrayList<Question> = arrayListOf(
        Question("В какой книге главный герой Гарри Поттер?", "ГП"),
        Question("В какой книге главная героиня Алиса?", "Алиса"),
        Question("В какой книге у мальчика на лбу шрам?", "ГП"),
        Question("Какая книга написана на русском языке?", "ПУ"),
        Question("Какую книгу написал Льюис Кэролл?", "Алиса"),
        Question("Какую книгу написал Чингиз Айтматов?", "ПУ"),
        Question("В какой книге нет волшебства?", "ПУ"),
        Question("В какой книге у мальчика есть волшебная палочка?", "ГП"),
        Question("В какой книге есть школа Хогвартс?", "ГП"),
        Question("В какой книге есть Белая Королева?", "Алиса")
    )

    private var quests: ArrayList<Question> = questions

    var questionsRight: ArrayList<String> = arrayListOf()
    var questionsWrong: ArrayList<String> = arrayListOf()
    var correctAnswers = 0


    var questlistResult : ArrayList<String> = arrayListOf()


    private var _questionDisplay = MutableLiveData<String>()
    val questionDisplay: LiveData<String>
        get() = _questionDisplay


    private val _gameOver = MutableLiveData<Boolean>(false)
    val gameOver: LiveData<Boolean>
        get() = _gameOver


    init {

        _questionDisplay.value = deriveQuestionDisplay()
    }


    fun deriveQuestionDisplay(): String {

        if (quests.size != 0) {
            var displayQ = quests.random()
            return displayQ.question
        }

        return ""


    }


    fun checkAnswer(q: String, answer: String): Boolean {
        var result = false

        for (i in quests) {
            if (i.question.equals(q) && i.answer.equals(answer)) {
                questionsRight.add(q)
                //questlistResult.add(Question(q, "+"))
                questlistResult.add("$q:\t(+)")
                correctAnswers++
                result = true
            } else if (i.question.equals(q) && !i.answer.equals(answer)) {
                questionsWrong.add(q)
                //questlistResult.add(Question(q, "-"))
                questlistResult.add("$q:\t(-)")
                result = false
            }
        }

        val indexQuestion = quests.indices.find { quests[it].question == q }
        if (indexQuestion != null) {
            quests.removeAt(indexQuestion)
        }

        if(isGameOver())
        {
            finishGame()
        }
        else
        {
            _questionDisplay.value = deriveQuestionDisplay()
        }



        return result
    }



    private fun isGameOver() = quests.size ?: 0 <= 0

    fun finishGame()
    {
        _gameOver.value = true
    }









}


