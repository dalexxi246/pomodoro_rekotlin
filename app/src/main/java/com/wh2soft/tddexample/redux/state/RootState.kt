package com.wh2soft.tddexample.redux.state

import org.rekotlin.StateType

data class RootState(
        val loading: Boolean = false,
        val projects: List<Project> = emptyList()
) : StateType {
}