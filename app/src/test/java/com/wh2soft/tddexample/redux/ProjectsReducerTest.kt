package com.wh2soft.tddexample.redux

import com.google.common.truth.Truth
import com.wh2soft.tddexample.redux.reducers.rootReducer
import com.wh2soft.tddexample.redux.state.*
import org.junit.Test

class ProjectsReducerTest {

    @Test fun `Given one projects in the state, when none of ProjectAction's child actions has been dispatched, then the projects state must have the same 2 projects`() {

        // region Given
        val actualProjects = listOf(Project(description = "Mock project"))
        val actualState = RootState(projects = actualProjects)
        // endregion

        // region When
        val newState = rootReducer(UselessAction(), actualState)
        // endregion

        // region Then
        Truth.assertThat(newState).isEqualTo(actualState)
        // endregion

    }

    @Test fun `Given 2 projects in the state, when CreateProject action has been dispatched with a new project, then the projects state must have 3 projects`() {

        // region Given
        val actualProjects = listOf(Project(description = "Mock project"), Project(description = "Mock project 2"))
        val actualState = RootState(projects = actualProjects)
        // endregion

        // region When
        val projectToAdd = Project(description = "New project")
        val newState = rootReducer(ProjectAction.CreateProject(projectToAdd), actualState)
        // endregion

        // region Then
        val expected = RootState(projects = actualProjects.plus(projectToAdd))
        Truth.assertThat(newState).isEqualTo(expected)
        // endregion

    }

    @Test fun `Given 2 projects in the state, when DeleteProject action has been dispatched with an existing project id, then the projects state must have only one project`() {

        // region Given
        val actualProjects = listOf(Project(id = 1), Project(id = 2))
        val actualState = RootState(projects = actualProjects)
        // endregion

        // region When
        val idForRemovalProject: Long = 1
        val newState = rootReducer(ProjectAction.RemoveProject(idForRemovalProject), actualState)
        // endregion

        // region Then
        val expected = RootState(projects = listOf(Project(id = 2)))
        Truth.assertThat(newState).isEqualTo(expected)
        // endregion

    }

    @Test fun `Given one project in the state, when UpdateProjectData action has been dispatched with an existing project, then the projects state must have the project modified`() {

        // region Given
        val actualProjects = listOf(Project(id = 1, description = "Mock project", color = null))
        val actualState = RootState(projects = actualProjects)
        // endregion

        // region When
        val newProjectData = Project(id = 1, description = "Project", color = Color.TEAL)
        val newState = rootReducer(ProjectAction.UpdateProjectData(newProjectData), actualState)
        // endregion

        // region Then
        val expected = RootState(projects = listOf(newProjectData))
        Truth.assertThat(newState).isEqualTo(expected)
        // endregion

    }

    @Test fun `Given one project with one task in the state, when AddNewTask action has been dispatched with a task, then the projects state must have the project with 2 tasks`() {

        // region Given
        val actualTasks = listOf(Task(description = "Task", id = 1))
        val actualProject = Project(id = 1, description = "Mock project", color = null, tasks = actualTasks)
        val actualState = RootState(projects = listOf(actualProject))
        // endregion

        // region When
        val newTask = Task(description = "New task", id = 2)
        val newState = rootReducer(ProjectAction.TaskAction.AddNewTask(newTask, actualProject.id), actualState)
        // endregion

        // region Then
        val expectedTasks = actualTasks.plus(newTask)
        val expectedState = RootState(projects = listOf(Project(id = 1, description = "Mock project", color = null, tasks = expectedTasks)))
        Truth.assertThat(newState).isEqualTo(expectedState)
        // endregion

    }

    @Test fun `Given one project with one task in the state, when DeleteTask action has been dispatched with a task, then the projects state must have the project without tasks`() {

        // region Given
        val actualTasks = listOf(Task(description = "Task", id = 1L))
        val actualProject = Project(id = 1, description = "Mock project", color = null, tasks = actualTasks)
        val actualState = RootState(projects = listOf(actualProject))
        // endregion

        // region When
        val newState = rootReducer(ProjectAction.TaskAction.DeleteTask(1L, actualProject.id), actualState)
        // endregion

        // region Then
        val expectedTasks = actualTasks.filterNot { it.id == 1L }
        val expectedState = RootState(projects = listOf(Project(id = 1, description = "Mock project", color = null, tasks = expectedTasks)))
        Truth.assertThat(newState).isEqualTo(expectedState)
        // endregion

    }

    @Test fun `Given one project with one task in the state, when EditTask action has been dispatched with a task, then the projects state must have the project with the task data updated`() {

        // region Given
        val actualTasks = listOf(Task(description = "Task", id = 1L))
        val actualProject = Project(id = 1, description = "Mock project", color = null, tasks = actualTasks)
        val actualState = RootState(projects = listOf(actualProject))
        // endregion

        // region When
        val taskToEdit = Task(id = 1L, description = "Task edited", estimatedPomodoros = 3, priority = Priority.IMPORTANT)
        val newState = rootReducer(ProjectAction.TaskAction.EditTask(taskToEdit, actualProject.id), actualState)
        // endregion

        // region Then
        val expectedTasks = listOf(taskToEdit)
        val expectedState = RootState(projects = listOf(Project(id = 1, description = "Mock project", color = null, tasks = expectedTasks)))
        Truth.assertThat(newState).isEqualTo(expectedState)
        // endregion

    }

}