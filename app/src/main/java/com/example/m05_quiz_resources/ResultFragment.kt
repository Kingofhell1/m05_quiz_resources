package com.example.m05_quiz_resources

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.transition.Explode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
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
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        // Анимируем кол-во подсчетов
        (AnimatorInflater.loadAnimator(this.context, R.animator.custom_animation) as ObjectAnimator).apply {
            target = binding.result
            start()
        }

        // Анимируем цвет названия кнопки
        ObjectAnimator.ofArgb(binding.buttonStartAgain,
            "textColor",
            Color.parseColor("#00000000"),
            Color.parseColor("#FFFFFFFF")).apply {
            duration = 3000
            repeatCount = ObjectAnimator.INFINITE
            start()

        }
        requireActivity().window.enterTransition = Explode()
        requireActivity().window.exitTransition = Explode()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartAgain.setOnClickListener {

            findNavController().navigate(R.id.action_ResultFragment_to_QuestionsFragment)
        }
        windowTransaction()

        val correctAnswersCount = arguments?.getInt("correctAnswersCount") ?: 0

        val resultTextView: TextView = view.findViewById(R.id.result)
        resultTextView.text = "Количество правильных ответов: $correctAnswersCount"

    }

    private fun windowTransaction(){
        val option = Explode()
        option.duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        enterTransition = option
        exitTransition = option
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}