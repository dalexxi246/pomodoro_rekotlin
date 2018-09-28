package com.wh2soft.tddexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wh2soft.tddexample.features.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow()
            // TODO Replace navigation with NavigationController
        }
    }

}
