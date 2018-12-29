package com.wh2soft.tddexample.features.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.wh2soft.tddexample.R
import com.wh2soft.tddexample.navigation.Router

class NavigationActivity : AppCompatActivity(), Router {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        getNavController().navigate(R.id.taskDetailsFragment, Bundle().apply {
            putInt("taskId", 1)
            putInt("projectId", 1)
        })
    }

    override fun goToTaskDetails(taskId: Int, projectId: Int) {
        getNavController().navigate(R.id.taskDetailsFragment)
    }

    private fun getNavController() = findNavController(R.id.nav_host_fragment)

}
