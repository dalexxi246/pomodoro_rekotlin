package com.wh2soft.tddexample.redux

import com.google.common.truth.Truth
import com.wh2soft.tddexample.redux.state.Project
import com.wh2soft.tddexample.redux.state.RootState
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

    @Test fun `Given 2 projects in the state, when DeleteProject action has been dispatched with an existing project, then the projects state must have only one project`() {

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

    // Validar la edicion del color de un proyecto especifico
    // Validar la edicion del nombre de un proyecto especifico

    // Validar que se agregue una tarea al proyecto indicado
    // Validar que se pueda editar una tarea de un proyecto especifico
    // Validar que

}