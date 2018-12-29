package com.wh2soft.tddexample.navigation

interface Router {
    fun goToTaskDetails(taskId: Int, projectId: Int)
}