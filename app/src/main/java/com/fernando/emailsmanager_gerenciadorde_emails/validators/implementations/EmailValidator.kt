package com.fernando.emailsmanager_gerenciadorde_emails.validators.implementations

import android.util.Patterns
import com.fernando.emailsmanager_gerenciadorde_emails.R
import com.fernando.emailsmanager_gerenciadorde_emails.exceptions.InvalidEmailException
import com.fernando.emailsmanager_gerenciadorde_emails.validators.Validator
import com.fernando.emailsmanager_gerenciadorde_emails.validators.ValidatorResult

class EmailValidator : Validator<String> {
    override fun isValid(emailValue: String): ValidatorResult {
        if (emailValue.isEmpty()) {
            return ValidatorResult(
                isValid = false,
                errorMessage = R.string.empty_email_error_text
            )
        }
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()

        if (!isValidEmail) {
            return ValidatorResult(
                isValid = false,
                errorMessage = R.string.invalid_email_error_text
            )
        }

        return ValidatorResult(
            isValid = true,
            errorMessage = null
        )
    }
}