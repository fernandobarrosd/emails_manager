package com.fernando.emailsmanager_gerenciadorde_emails.validators

interface Validator<T> {
    fun isValid(value: T) : ValidatorResult
}