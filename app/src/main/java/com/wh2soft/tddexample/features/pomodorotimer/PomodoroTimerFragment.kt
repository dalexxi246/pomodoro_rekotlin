package com.wh2soft.tddexample.features.pomodorotimer

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wh2soft.tddexample.R

class PomodoroTimerFragment : Fragment() {

    companion object {
        fun newInstance() = PomodoroTimerFragment()
    }

    private lateinit var viewModel: PomodoroTimerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pomodoro_timer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PomodoroTimerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
