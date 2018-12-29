package com.wh2soft.tddexample.redux.epics

import com.wh2soft.tddexample.R
import com.wh2soft.tddexample.navigation.Router
import com.wh2soft.tddexample.redux.ActionsObservable
import com.wh2soft.tddexample.redux.Epic
import com.wh2soft.tddexample.redux.NavigationAction
import io.reactivex.Observable
import org.rekotlin.Action
import org.rekotlin.StateType
import org.rekotlin.Store

class NavigationEpic(private val router: Router) : Epic {

    override fun invoke(actions: ActionsObservable, store: Store<out StateType>): ActionsObservable {
        return actions.filter { it is NavigationAction }
                .doOnNext { navigate(it) }
                .flatMap { Observable.empty<Action>() }
    }

    private fun navigate(action: Action) {
        when (action) {
            is NavigationAction.GoToTaskDetail -> router.goToTaskDetails(action.taskId, action.projectId)
            is NavigationAction.GoToTaskList -> R.id.navigateToNotesList
            is NavigationAction.OpenProjectsList -> R.id.navigateToProjectsListFragment
        }
    }

}