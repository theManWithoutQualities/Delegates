package com.konst007.delegates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

class MainActivity : AppCompatActivity() {

    private val scope = MainScope()

    private var changed by ChangedDelegate(scope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val button = findViewById<View>(R.id.button)
        button.setOnClickListener {
            changed = !changed
        }
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}