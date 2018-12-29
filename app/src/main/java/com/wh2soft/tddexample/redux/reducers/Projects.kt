package com.wh2soft.tddexample.redux.reducers

import com.wh2soft.tddexample.redux.ProjectAction
import com.wh2soft.tddexample.redux.TaskAction
import com.wh2soft.tddexample.redux.state.Project
import com.wh2soft.tddexample.redux.state.Task
import org.rekotlin.Action

object Projects {

    internal fun projectsReducer(action: Action, state: List<Project>?): List<Project> {
        val projectsState = state ?: emptyList()
        return when (action) {
            is ProjectAction.CreateProject -> projectsState.plus(action.project)
            is ProjectAction.RemoveProject -> projectsState.filterNot { action.idForRemovalProject == it.id }
            is ProjectAction.UpdateProjectData -> projectsState.asSequence().filter { it.id == action.newProjectData.id }.map { action.newProjectData }.toList()
            is TaskAction.AddNewTask -> projectsState.map { project -> project.mapTasks(action.projectId, addTask(project, action)) }
            is TaskAction.DeleteTask -> projectsState.map { project -> project.mapTasks(action.projectId, deleteTask(project, action)) }
            is TaskAction.EditTask -> projectsState.map { project -> project.mapTasks(action.projectId, changeTask(project, action)) }
            is TaskAction.MarkTaskAsCompleted -> projectsState.map { project -> project.mapTasks(action.projectId, markTaskAsCompleted(project, action)) }
            is TaskAction.MarkTaskAsNotCompleted -> projectsState.map { project -> project.mapTasks(action.projectId, markTaskAsNotCompleted(project, action)) }
            else -> projectsState
        }
    }

    private fun Project.mapTasks(projectId: Int, tasksMapper: () -> List<Task>): Project {
        return if (this.id == projectId) {
            val newTasks = tasksMapper()
            this.copy(tasks = newTasks)
        } else {
            this
        }
    }

    private fun addTask(project: Project, action: TaskAction.AddNewTask): () -> List<Task> {
        return { project.tasks.plus(action.task) }
    }

    private fun deleteTask(project: Project, action: TaskAction.DeleteTask): () -> List<Task> {
        return { project.tasks.filterNot { task -> task.id == action.taskToDeleteId } }
    }

    private fun changeTask(project: Project, action: TaskAction.EditTask): () -> List<Task> {
        return {
            project.tasks.asSequence()
                    .filter { task -> task.id == action.taskToEdit.id }
                    .map { action.taskToEdit }
                    .toList()
        }
    }

    private fun markTaskAsCompleted(project: Project, action: TaskAction.MarkTaskAsCompleted): () -> List<Task> {
        return {
            project.tasks.asSequence()
                    .filter { task -> task.id == action.taskId }
                    .map { it.copy(completed = true) }
                    .toList()
        }
    }

    private fun markTaskAsNotCompleted(project: Project, action: TaskAction.MarkTaskAsNotCompleted): () -> List<Task> {
        return {
            project.tasks.asSequence()
                    .filter { task -> task.id == action.taskId }
                    .map { it.copy(completed = false) }
                    .toList() }
    }

}