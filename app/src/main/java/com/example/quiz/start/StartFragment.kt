package com.example.quiz.start

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }

    private var _binding : FragmentStartBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: StartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(StartViewModel::class.java)

        binding.buttonPlay.setOnClickListener {
            val action = StartFragmentDirections
                .actionStartFragmentToQuestionFragment()
            view.findNavController().navigate(action)
        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}