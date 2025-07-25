package com.fernando.emailsmanager_gerenciadorde_emails.validators

import androidx.annotation.StringRes

data class ValidatorResult(val isValid: Boolean, @StringRes val errorMessage: Int?)