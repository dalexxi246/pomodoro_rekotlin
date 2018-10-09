package com.wh2soft.tddexample.redux

import android.util.Log
import com.wh2soft.tddexample.redux.epics.exampleEpic
import com.wh2soft.tddexample.redux.reducers.rootReducer
import com.wh2soft.tddexample.redux.state.RootState
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.rekotlin.Action
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware
import org.rekotlin.Store

val epicsMiddleware by lazy { EpicsMiddleware(listOf(exampleEpic)) }

val store by lazy { buildStore() }

fun buildStore(): Store<RootState> {
    return Store(
            reducer = ::rootReducer,
            middleware = listOf(epicsMiddleware),
            state = RootState(),
            automaticallySkipRepeats = true
    )
}

class EpicsMiddleware(private val epics: List<Epic>) : Middleware<RootState>, Disposable {

    private var epicsDisposable: Disposable? = null

    override fun invoke(dispatch: DispatchFunction, state: () -> RootState?): (DispatchFunction) -> DispatchFunction =
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