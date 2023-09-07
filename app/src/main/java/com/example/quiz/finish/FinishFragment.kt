package com.example.quiz.finish

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.quiz.Question
import com.example.quiz.R

class FinishFragment : Fragment() {



    companion object {
        fun newInstance() = FinishFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finish, container, false)

        val answers = FinishFragmentArgs.fromBundle(requireArguments()).resultList

        val countCorrectAnswers = FinishFragmentArgs.fromBundle(requireArguments()).countCorrectAnswers

        val countCorrectAnswersTextView = view.findViewById<TextView>(R.id.count_correct_answers)
        val rAnswerListView = view.findViewById<ListView>(R.id.rightAnswersListView)

        val resultText = String.format(getString(R.string.count_right_answers,countCorrectAnswers.toString()) )
        countCorrectAnswersTextView.setText(resultText)

        val adapter1 = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, answers)

        rAnswerListView.adapter = adapter1







        return view
    }


}


