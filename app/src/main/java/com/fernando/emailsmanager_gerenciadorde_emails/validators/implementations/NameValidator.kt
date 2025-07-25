package com.fernando.emailsmanager_gerenciadorde_emails.validators.implementations

import com.fernando.emailsmanager_gerenciadorde_emails.R
import com.fernando.emailsmanager_gerenciadorde_emails.exceptions.InvalidNameException
import com.fernando.emailsmanager_gerenciadorde_emails.validators.Validator
import com.fernando.emailsmanager_gerenciadorde_emails.validators.ValidatorResult

class NameValidator : Validator<String> {
    override fun isValid(nameValue: String): ValidatorResult {
        if (nameValue.isEmpty()) {
            return ValidatorResult(
                isValid = false,
                errorMessage = R.string.empty_name_error_text
            )
        }
        if (nameValue.length == 1) {
            return return ValidatorResult(
                isValid = false,
                errorMessage = R.string.invalid_name_error_text
            )
        }
        return ValidatorResult(
            isValid = true,
            errorMessage = null
        )
    }
}