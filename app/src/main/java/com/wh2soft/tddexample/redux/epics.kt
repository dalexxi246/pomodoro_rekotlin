package com.wh2soft.tddexample.redux

import io.reactivex.Observable
import org.rekotlin.Action
import org.rekotlin.StateType
import org.rekotlin.Store

typealias ActionsObservable = Observable<out Action>
typealias Epic = (ActionsObservable, Store<out StateType>) -> ActionsObservable

fun combineEpics(action: ActionsObservable, epics: List<Epic>) : Observable<in Action> {
    return Observable.fromIterable(epics).flatMap { epic -> epic(action, store) }
}