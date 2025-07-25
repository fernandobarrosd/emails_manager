package com.fernando.emailsmanager_gerenciadorde_emails.ui

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fernando.emailsmanager_gerenciadorde_emails.R

class MainViewModel : ViewModel() {
    private val _isShowBottomBar: MutableLiveData<Boolean> = MutableLiveData()
    val isShowBottomBar: LiveData<Boolean>
        get() = _isShowBottomBar

    @IdRes
    private val _destinationsWithBottomBar: List<Int> = listOf(
        R.id.homeFragment
    )

    fun onDestinationChange(@IdRes destinationId: Int) {
        val isShowBottomBar = _destinationsWithBottomBar
            .any { it == destinationId }

        _isShowBottomBar.postValue(isShowBottomBar)
    }
}