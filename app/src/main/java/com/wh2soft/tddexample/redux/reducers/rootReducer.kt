package com.wh2soft.tddexample.redux.reducers

import com.wh2soft.tddexample.redux.reducers.Projects.projectsReducer
import com.wh2soft.tddexample.redux.state.RootState
import org.rekotlin.Action

fun rootReducer(action: Action, rootState: RootState?): RootState {
    val state = rootState ?: RootState()
    return state.copy(
            projects = (::projectsReducer)(action, state.projects)
    )
}

