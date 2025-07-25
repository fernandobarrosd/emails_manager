package com.fernando.emailsmanager_gerenciadorde_emails.ui.screens.emails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.emailsmanager_gerenciadorde_emails.models.Email
import com.fernando.emailsmanager_gerenciadorde_emails.repositories.EmailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailsViewModel @Inject constructor(private val emailRepository: EmailRepository)
    : ViewModel() {
    private val _emails: MutableLiveData<List<Email>> = MutableLiveData()
    val emails: LiveData<List<Email>>
        get() = _emails


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _showingState: MutableLiveData<ShowingState> = MutableLiveData()
    val showingState: LiveData<ShowingState>
        get() = _showingState

    fun getAllEmails() {
        viewModelScope.launch {
            _isLoading.postValue(true)

            try {
                delay(3000L)
                val emails = emailRepository.findAllEmails()
                _emails.postValue(emails)

                if (emails.isEmpty()) {
                    _showingState.postValue(ShowingState.WARNING)
                    return@launch
                }
                _showingState.postValue(ShowingState.LIST)
            }
            finally {
                _isLoading.postValue(false)
            }
        }
    }
}