package com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun AppCompatActivity.findNavController(@IdRes viewId: Int) : NavController {
    val navHostFragment = supportFragmentManager.findFragmentById(viewId) as NavHostFragment
    return navHostFragment.navController
}

fun <T> AppCompatActivity.observeLiveData(liveData: LiveData<T>, observer: Observer<T>) {
    liveData.observe(this, observer)
}

fun AppCompatActivity.getNavController(@IdRes viewId: Int) : ReadOnlyProperty<AppCompatActivity, NavController> {
    return object : ReadOnlyProperty<AppCompatActivity, NavController> {
        private val navController by lazy {
            findNavController(viewId)
        }
        override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): NavController {
            return navController
        }

    }
}