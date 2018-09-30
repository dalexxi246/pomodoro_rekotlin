package com.wh2soft.tddexample.features.projects

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wh2soft.tddexample.R

class ProjectsListFragment : Fragment() {

    companion object {
        fun newInstance() = ProjectsListFragment()
    }

    private lateinit var viewModel: ProjectsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_projects_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProjectsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
