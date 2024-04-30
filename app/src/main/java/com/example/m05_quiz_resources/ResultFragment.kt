package com.example.m05_quiz_resources

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.m05_quiz_resources.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding?  = null
    private val binding get () = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonStartAgain.setOnClickListener {
            findNavController().navigate(R.id.action_ResultFragment_to_QuestionsFragment)
        }
        val correctAnswersCount = arguments?.getInt("correctAnswersCount") ?: 0

        val resultTextView: TextView = view.findViewById(R.id.result)
        resultTextView.text = "Количество правильных ответов: $correctAnswersCount"
    }



}