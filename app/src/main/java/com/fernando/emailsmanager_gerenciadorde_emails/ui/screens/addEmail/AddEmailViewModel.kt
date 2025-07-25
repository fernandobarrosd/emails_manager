package com.fernando.emailsmanager_gerenciadorde_emails.ui.screens.addEmail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.emailsmanager_gerenciadorde_emails.R
import com.fernando.emailsmanager_gerenciadorde_emails.models.Email
import com.fernando.emailsmanager_gerenciadorde_emails.repositories.EmailRepository
import com.fernando.emailsmanager_gerenciadorde_emails.validators.ValidatorResult
import com.fernando.emailsmanager_gerenciadorde_emails.validators.implementations.EmailValidator
import com.fernando.emailsmanager_gerenciadorde_emails.validators.implementations.NameValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEmailViewModel @Inject constructor(
    private val emailRepository: EmailRepository,
    private val nameValidator: NameValidator,
    private val emailValidator: EmailValidator) : ViewModel() {

    private val _invalidNameErrorMessage: MutableLiveData<Int?> = MutableLiveData(null)
    val invalidNameErrorMessage: LiveData<Int?>
        get() = _invalidNameErrorMessage

    private val _invalidEmailErrorMessage: MutableLiveData<Int?> = MutableLiveData(null)
    val invalidEmailErrorMessage: LiveData<Int?>
        get() = _invalidEmailErrorMessage

    private val _errorText: MutableLiveData<Int?> = MutableLiveData(null)
    val errorText: LiveData<Int?>
        get() = _errorText

    private val _isValidFields : MutableLiveData<Boolean> = MutableLiveData()

    private val _isSavedWithSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isSavedWithSuccess : LiveData<Boolean>
        get() = _isSavedWithSuccess

    private val _emailIsAlreadyExists : MutableLiveData<Boolean> = MutableLiveData()

    private fun validateName(name: String) : Boolean {
        val isValidNameResult = nameValidator.isValid(name)
        val ( isValidName, errorMessage ) = isValidNameResult

        _invalidNameErrorMessage.value = errorMessage

        return isValidName
    }

    private fun validateEmail(email: String) : Boolean {
        val isValidEmailResult = emailValidator.isValid(email)
        val ( isValidEmail, errorMessage) = isValidEmailResult

        _invalidEmailErrorMessage.postValue(errorMessage)

        return isValidEmail
    }

    fun saveEmail(email: Email) {
        val emailName = email.name
        val emailValue = email.value

        val isValidName = validateName(emailName)
        val isValidEmail = validateEmail(emailValue)

        val allFieldsIsValid = isValidName && isValidEmail
       _isValidFields.postValue(allFieldsIsValid)

        viewModelScope.launch {
            if (!allFieldsIsValid) {
                _isSavedWithSuccess.postValue(false)
                return@launch
            }

            val emailIsExistsByName = emailRepository.existsEmailByName(emailName)
            val emailIsExists = emailRepository.existsEmail(emailValue)

            val emailIsAlreadyExists = emailIsExistsByName || emailIsExists
            _emailIsAlreadyExists.postValue(emailIsAlreadyExists)

            if (emailIsAlreadyExists) {
                _errorText.postValue(R.string.add_email_error_text)
                _isSavedWithSuccess.postValue(false)
                return@launch
            }

            emailRepository.saveEmail(email)
            _isSavedWithSuccess.postValue(true)
        }
    }
}