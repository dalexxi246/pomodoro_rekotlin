package com.wh2soft.tddexample.redux

import android.util.Log
import com.wh2soft.tddexample.di.store
import com.wh2soft.tddexample.redux.state.RootState
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.rekotlin.*

typealias ActionsObservable = Observable<out Action>
typealias Epic = (ActionsObservable, Store<out StateType>) -> ActionsObservable

fun combineEpics(action: ActionsObservable, epics: List<Epic>) : Observable<in Action> {
    return Observable.fromIterable(epics).flatMap { epic -> epic(action, store) }
}

class EpicsMiddleware(private val epics: List<Epic>) : Middleware<RootState>, Disposable {

    private var epicsDisposable: Disposable? = null

    override operator fun invoke(dispatch: DispatchFunction, state: () -> RootState?): (DispatchFunction) -> DispatchFunction =
            { next ->
                { action ->
                    next(action)
                    val actionsPublisher = PublishSubject.create<Action>()
                    epicsDisposable = combineEpics(actionsPublisher, epics).subscribe(
                            { if (it is Action) dispatch(it) },
                            { throwable -> Log.e(EpicsMiddleware::class.java.simpleName, throwable.message) }
                    )
                    actionsPublisher.onNext(action)
                }
            }

    override fun isDisposed(): Boolean {
        return epicsDisposable?.isDisposed ?: true
    }

    override fun dispose() {
        epicsDisposable?.dispose()
    }
}