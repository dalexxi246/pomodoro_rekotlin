package com.wh2soft.tddexample.redux

import android.os.Bundle
import com.wh2soft.tddexample.redux.state.Project
import com.wh2soft.tddexample.redux.state.Task
import org.rekotlin.Action

sealed class ProjectAction : Action {
    data class CreateProject(val project: Project) : ProjectAction()
    data class RemoveProject(val idForRemovalProject: Int) : ProjectAction()
    data class UpdateProjectData(val newProjectData: Project) : ProjectAction()
}

sealed class TaskAction : Action {
    data class AddNewTask(val task: Task, val projectId: Int) : TaskAction()
    data class DeleteTask(val taskToDeleteId: Int, val projectId: Int) : TaskAction()
    data class EditTask(val taskToEdit: Task, val projectId: Int) : TaskAction()
    data class MarkTaskAsCompleted(val taskId: Int, val projectId: Int) : TaskAction()
    data class MarkTaskAsNotCompleted(val taskId: Int, val projectId: Int) : TaskAction()
}

sealed class NavigationAction : Action {
    data class GoToTaskDetail(val taskId: Int, val projectId: Int) : NavigationAction()
    data class GoToTaskList(val projectId: Int) : NavigationAction()
    object OpenProjectsList : NavigationAction()
}

class UselessAction : Action
