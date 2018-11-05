package com.wh2soft.tddexample.redux

import com.wh2soft.tddexample.redux.epics.exampleEpic
import com.wh2soft.tddexample.redux.epics.OtroEpic
import com.wh2soft.tddexample.redux.reducers.rootReducer
import com.wh2soft.tddexample.redux.state.RootState
import org.rekotlin.Store

val epicsMiddleware by lazy { EpicsMiddleware(listOf(exampleEpic, OtroEpic())) }

val store by lazy { buildStore() }

fun buildStore(): Store<RootState> {
    return Store(
            reducer = ::rootReducer,
            middleware = listOf(epicsMiddleware),
            state = RootState(),
            automaticallySkipRepeats = true
    )
}
