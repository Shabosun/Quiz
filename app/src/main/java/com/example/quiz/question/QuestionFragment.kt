package com.example.quiz.question

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {



    companion object {
        fun newInstance() = QuestionFragment()
    }

    private var _binding : FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        binding.questionViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.gameOver.observe(viewLifecycleOwner, Observer {newValue ->
            if(newValue)
            {
                val lists = arrayOf("fd", "fk")
                val action = QuestionFragmentDirections.actionQuestionFragmentToFinishFragment(
                    viewModel.questlistResult.toTypedArray(),
                    viewModel.correctAnswers
                )

                view.findNavController().navigate(action)
            }
        })




        binding.hpButton.setOnClickListener{

            viewModel.checkAnswer(binding.question.text.toString(), "ГП")

        }

        binding.aliceButton.setOnClickListener{

            viewModel.checkAnswer(binding.question.text.toString(), "Алиса")


        }
        binding.firstTeacherButton.setOnClickListener{
            viewModel.checkAnswer(binding.question.text.toString(), "ПУ")

        }



        return view
    }


}