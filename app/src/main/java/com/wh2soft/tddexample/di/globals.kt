package com.wh2soft.tddexample.di

import com.wh2soft.tddexample.navigation.Router
import com.wh2soft.tddexample.redux.EpicsMiddleware
import com.wh2soft.tddexample.redux.epics.NavigationEpic
import com.wh2soft.tddexample.redux.epics.OtroEpic
import com.wh2soft.tddexample.redux.epics.exampleEpic
import com.wh2soft.tddexample.redux.reducers.rootReducer
import com.wh2soft.tddexample.redux.state.RootState
import org.rekotlin.Store

val router = object : Router {
    override fun goToTaskDetails(taskId: Int, projectId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

val epicsMiddleware by lazy { EpicsMiddleware(listOf(exampleEpic, OtroEpic(), NavigationEpic(router))) }

val store by lazy { buildStore() }

fun buildStore(): Store<RootState> {
    return Store(
            reducer = ::rootReducer,
            middleware = listOf(epicsMiddleware),
            state = RootState(),
            automaticallySkipRepeats = true
    )
}
