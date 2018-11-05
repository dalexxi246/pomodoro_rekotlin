package com.wh2soft.tddexample.redux.epics

import com.wh2soft.tddexample.redux.ActionsObservable
import com.wh2soft.tddexample.redux.Epic
import io.reactivex.Observable
import org.rekotlin.Action
import org.rekotlin.StateType
import org.rekotlin.Store

val exampleEpic : Epic = { actions, store ->
    actions.flatMap { Observable.empty<Action>() }
}

class OtroEpic : Epic {
    override fun invoke(actions: ActionsObservable, store: Store<out StateType>): ActionsObservable {
        return actions.flatMap { Observable.empty<Action>() }
    }
}