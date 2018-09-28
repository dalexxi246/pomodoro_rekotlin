package com.wh2soft.tddexample.features.taskslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wh2soft.tddexample.R


class TasksListFragment : Fragment() {

    companion object {
        fun newInstance() = TasksListFragment()
    }

    private lateinit var viewModel: TasksListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.notes_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TasksListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
