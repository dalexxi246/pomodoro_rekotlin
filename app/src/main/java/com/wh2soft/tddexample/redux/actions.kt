package com.wh2soft.tddexample.redux

import com.wh2soft.tddexample.redux.state.Project
import com.wh2soft.tddexample.redux.state.Task
import org.rekotlin.Action

sealed class ProjectAction : Action {

    class CreateProject(val project: Project) : ProjectAction()
    class RemoveProject(val idForRemovalProject: Long) : ProjectAction()
    class UpdateProjectData(val newProjectData: Project) : ProjectAction()

    sealed class TaskAction : ProjectAction() {
        class AddNewTask(val task: Task, val projectId: Long) : TaskAction()
        class DeleteTask(val taskToDeleteId: Long, val projectId: Long) : TaskAction()
        class EditTask(val taskToEdit: Task, val projectId: Long) : TaskAction()
    }

}

class UselessAction : Action
