package com.fernando.emailsmanager_gerenciadorde_emails.models

import java.util.UUID

class Email {
    private val _id: String
    private var _name: String
    private var _value: String

    val id: String
        get() = _id

    val name: String
        get() = _name

    val value: String
        get() = _value

    constructor(name: String, email: String) {
        this._id = UUID.randomUUID().toString()
        this._name = name
        this._value = email
    }

    constructor(id: String, name: String, email: String) {
        this._id = id
        this._name = name
        this._value = email
    }

    fun updateEmailValue(newEmailValue: String) {
        this._value = newEmailValue
    }

    fun updateEmailName(newEmailName: String) {
        this._name = newEmailName
    }
}