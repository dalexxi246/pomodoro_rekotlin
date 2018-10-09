package com.wh2soft.tddexample.redux.reducers

import com.wh2soft.tddexample.redux.ProjectAction
import com.wh2soft.tddexample.redux.state.Project
import com.wh2soft.tddexample.redux.state.Task
import org.rekotlin.Action

object Projects {

    fun projectsReducer(action: Action, state: List<Project>?): List<Project> {
        val projectsState = state ?: emptyList()
        return when (action) {
            is ProjectAction.CreateProject -> projectsState.plus(action.project)
            is ProjectAction.RemoveProject -> projectsState.filterNot { action.idForRemovalProject == it.id }
            is ProjectAction.UpdateProjectData -> projectsState.asSequence().filter { it.id == action.newProjectData.id }.map { action.newProjectData }.toList()
            is ProjectAction.TaskAction.AddNewTask -> projectsState.map { project -> project.mapTasks(action.projectId, addTask(project, action)) }
            is ProjectAction.TaskAction.DeleteTask -> projectsState.map { project -> project.mapTasks(action.projectId, deleteTask(project, action)) }
            is ProjectAction.TaskAction.EditTask -> projectsState.map { project -> project.mapTasks(action.projectId, changeTask(project, action)) }
            else -> projectsState
        }
    }

    private fun Project.mapTasks(projectId: Long, tasksMapper: () -> List<Task>): Project {
        return if (this.id == projectId) {
            val newTasks = tasksMapper()
            this.copy(tasks = newTasks)
        } else {
            this
        }
    }

    private fun addTask(project: Project, action: ProjectAction.TaskAction.AddNewTask) = { project.tasks.plus(action.task) }

    private fun changeTask(project: Project, action: ProjectAction.TaskAction.EditTask) = { project.tasks.asSequence().filter { task -> task.id == action.taskToEdit.id }.map { action.taskToEdit }.toList() }

    private fun deleteTask(project: Project, action: ProjectAction.TaskAction.DeleteTask) = { project.tasks.filterNot { task -> task.id == action.taskToDeleteId } }

}