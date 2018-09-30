package com.wh2soft.tddexample.redux.epics

import com.wh2soft.tddexample.redux.Epic
import io.reactivex.Observable
import org.rekotlin.Action

val exampleEpic : Epic = { actions, store -> actions.flatMap { Observable.empty<Action>() } }