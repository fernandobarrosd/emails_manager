package com.fernando.emailsmanager_gerenciadorde_emails.validators

import com.fernando.emailsmanager_gerenciadorde_emails.validators.implementations.EmailValidator
import com.fernando.emailsmanager_gerenciadorde_emails.validators.implementations.NameValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ValidatorModule {
    @Provides
    @Singleton
    fun nameValidator() : NameValidator {
        return NameValidator()
    }

    @Provides
    @Singleton
    fun emailValidator() : EmailValidator {
        return EmailValidator()
    }
}