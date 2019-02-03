package com.wh2soft.tddexample.databinding

import android.widget.TextView
import com.wh2soft.tddexample.R
import org.jetbrains.anko.*

class MainActivityUI(private val bindText: Binder<String>) : AnkoComponent<MainActivity> {

    private lateinit var textview: TextView

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            textview = textView {
                id = R.id.textview_databinding
                bind(bindText) { text = it }
            }
            val name = editText()
            button("Say Hello") {
                setOnClickListener { (ctx as MainActivity).changeData("${name.text}") }
            }
        }
    }

    fun unBind() {
        textview.unBind(bindText)
    }
}