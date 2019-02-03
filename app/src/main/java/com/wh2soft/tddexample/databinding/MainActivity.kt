package com.wh2soft.tddexample.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    private val bindText = Binder("Oh, Hi Mark.")
    private val component = MainActivityUI(bindText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.setContentView(this)
    }

    fun changeData(text: String) {
        bindText.item = text
    }

    override fun onDestroy() {
        super.onDestroy()
        component.unBind()
    }
}
