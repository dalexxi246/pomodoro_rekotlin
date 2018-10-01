package com.wh2soft.tddexample.redux

import com.google.common.truth.Truth
import com.wh2soft.tddexample.redux.state.Color
import com.wh2soft.tddexample.redux.state.Project
import com.wh2soft.tddexample.redux.state.RootState
import com.wh2soft.tddexample.redux.state.Task
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

    // Validar que se agregue una tarea al proyecto indicado (descripcion es obligatoria)
    // Validar que se pueda editar una tarea de un proyecto especifico
    // Validar que

}