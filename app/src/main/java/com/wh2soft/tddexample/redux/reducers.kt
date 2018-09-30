package com.wh2soft.tddexample.redux

import com.wh2soft.tddexample.redux.state.Project
import com.wh2soft.tddexample.redux.state.RootState
import org.rekotlin.Action

fun rootReducer(action: Action, rootState: RootState?): RootState {
    val state = rootState ?: RootState()
    return state.copy(
            projects = (::projectsReducer)(action, state.projects)
    )
}

fun projectsReducer(action: Action, state: List<Project>?): List<Project> {
    val projectsState = state ?: emptyList()
    return when (action) {
        is ProjectAction.CreateProject -> projectsState.plus(action.project)
        is ProjectAction.RemoveProject -> projectsState.filterNot { action.idForRemovalProject == it.id }
        else -> projectsState
    }
}
