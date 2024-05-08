package com.example.m05_quiz_resources

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.m05_quiz_resources.databinding.FragmentWelcomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private val calendar = Calendar.getInstance()
    // Будем выводить дату рождения в уведомлении пользователя для сокращения кода в кнопке birthdayDateButton
    private val dateFormatter = SimpleDateFormat("dd.MM.yyyy")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {

            findNavController().navigate(R.id.action_StartFragment_to_QuestionsFragment)
        }

        windowTransaction()

        binding.birthdayDateButton.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.enter_title_birthday))
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
                Snackbar.make(binding.birthdayDateButton,
                    dateFormatter.format(calendar.time),
                    Snackbar.LENGTH_SHORT).show()
            }
            dateDialog.show(parentFragmentManager, "DatePicker")
        }

    }

    private fun windowTransaction(){
        val option = Explode()
        option.duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        enterTransition = option
        exitTransition = option
    }



}