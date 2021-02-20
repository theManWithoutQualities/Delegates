package com.konst007.delegates

import android.app.Activity
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ChangedDelegate(private val scope: CoroutineScope) : ReadWriteProperty<Activity, Boolean> {

    private var value = false
    private var job: Job? = null

    override fun setValue(thisRef: Activity, property: KProperty<*>, value: Boolean) {
        val view = thisRef.findViewById<TextView>(R.id.text)
        if (value) {
            job?.cancel()
            job = scope.launch {
                delay(1000)
                this@ChangedDelegate.value = value
                view.text = "Hello, Changed World!"
            }
        } else {
            job?.cancel()
            this.value = value
            view.text = "Hello, World!"
        }
    }

    override fun getValue(thisRef: Activity, property: KProperty<*>): Boolean {
        return value
    }
}