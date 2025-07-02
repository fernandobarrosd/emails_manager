package com.fernando.emailsmanager_gerenciadorde_emails.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.emailsmanager_gerenciadorde_emails.models.Email
import com.fernando.emailsmanager_gerenciadorde_emails.repositories.EmailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailsViewModel @Inject constructor(private val emailRepository: EmailRepository)
    : ViewModel() {
    private val _emails: MutableLiveData<List<Email>> = MutableLiveData()
    val emails: LiveData<List<Email>>
        get() = _emails

    fun getAllEmails() {
        viewModelScope.launch {
            val emails = List(20) {
                Email(name = "Teste $it", email = "test$it@gmail.com")
            }
            _emails.postValue(emails)
        }
    }
}