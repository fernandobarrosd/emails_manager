package com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavController(@IdRes viewId: Int) : NavController {
    val navHostFragment = supportFragmentManager.findFragmentById(viewId) as NavHostFragment
    return navHostFragment.navController
}