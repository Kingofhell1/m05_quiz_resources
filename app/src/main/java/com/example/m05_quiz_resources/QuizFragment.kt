package com.example.m05_quiz_resources

import android.os.Bundle
import android.transition.Explode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m05_quiz_resources.databinding.FragmentQuizBinding
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding?  = null
    private val binding get () = _binding!!

    private fun calculateCorrectAnswers(): Int {
        var correctAnswersCount = 0

        if (binding.radioGroupQuestion1.checkedRadioButtonId == binding.answer2question1.id) correctAnswersCount++
        if (binding.radioGroupQuestion2.checkedRadioButtonId == binding.answer1question2.id) correctAnswersCount++
        if (binding.radioGroupQuestion3.checkedRadioButtonId == binding.answer2question3.id) correctAnswersCount++
        if (binding.radioGroupQuestion4.checkedRadioButtonId == binding.answer1question4.id) correctAnswersCount++
        if (binding.radioGroupQuestion5.checkedRadioButtonId == binding.answer5question1.id) correctAnswersCount++
        if (binding.radioGroupQuestion6.checkedRadioButtonId == binding.answer1question6.id) correctAnswersCount++


        return correctAnswersCount

    }
//    private fun isAnswerCorrect(selectedAnswerId: Int, correctAnswerId: Int): Boolean {
//        return selectedAnswerId == correctAnswerId
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater)

        binding.headerQuestions.alpha = 0f
        binding.headerQuestions.animate().apply {
            duration = 3000
            alpha(  1.0f)
        }

        return binding.root

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSend.setOnClickListener {
            val number = calculateCorrectAnswers()
            val bundle = Bundle().apply {
                putInt("correctAnswersCount", number)
            }

            findNavController().navigate(R.id.action_QuestionsFragment_to_ResultFragment, bundle)
        }
        windowTransaction()

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