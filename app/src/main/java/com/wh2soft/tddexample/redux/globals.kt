package com.wh2soft.tddexample.redux

import android.util.Log
import com.wh2soft.tddexample.redux.epics.exampleEpic
import com.wh2soft.tddexample.redux.state.RootState
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.rekotlin.*

val epicsMiddleware = EpicsMiddleware(listOf(exampleEpic))

val store = Store(
        reducer = ::rootReducer,
        middleware = listOf(epicsMiddleware),
        state = RootState(),
        automaticallySkipRepeats = true
)

fun rootReducer(action: Action, rootState: RootState?): RootState {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

