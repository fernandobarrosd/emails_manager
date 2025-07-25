package com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, observer: Observer<T>) {
    liveData.observe(viewLifecycleOwner, observer)
}

fun Fragment.getNavController() : ReadOnlyProperty<Fragment, NavController> {
    return object : ReadOnlyProperty<Fragment, NavController> {
        private val navController : NavController by lazy {
            findNavController()
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): NavController {
            return navController
        }
    }
}