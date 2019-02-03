package com.wh2soft.tddexample.databinding

import android.view.View
import kotlin.properties.Delegates

class Binder<T>(initValue: T) {

    private val bound: MutableMap<Int, (item: T) -> Unit> = HashMap()

    var item: T by Delegates.observable(initValue) { prop, old, new ->
        if (old != new) {
            bound.values.forEach {
                it(new)
            }
        }
    }

    fun bind(id: Int, binding: (item: T) -> Unit) {
        bound[id] = binding
        binding(item)
    }

    fun unBind(id: Int) = bound.remove(id)

}

fun <T> View.bind(binder: Binder<T>, binding: (item: T) -> Unit) = binder.bind(this.id, binding)
fun <T> View.unBind(binder: Binder<T>) = binder.unBind(this.id)