package com.wh2soft.tddexample.features.tasks

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wh2soft.tddexample.R

class TaskDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = TaskDetailsFragment()
    }

    private lateinit var viewModel: TaskDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TaskDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
