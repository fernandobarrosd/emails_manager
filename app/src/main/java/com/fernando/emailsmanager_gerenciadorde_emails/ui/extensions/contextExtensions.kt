package com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions

import android.content.Context
import android.view.LayoutInflater

fun Context.getLayoutInflater() : LayoutInflater {
    return LayoutInflater.from(this)
}