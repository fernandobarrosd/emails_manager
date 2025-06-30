package com.fernando.emailsmanager_gerenciadorde_emails.mappers

import com.fernando.emailsmanager_gerenciadorde_emails.models.Email
import com.fernando.emailsmanager_gerenciadorde_emails.room.entities.EmailEntity

fun Email.toRoomEntity() : EmailEntity {
    return EmailEntity(
        id = id,
        name = name,
        email = value
    )
}

fun EmailEntity.toModelEntity() : Email {
    return Email(
        id = id,
        name = name,
        email = email
    )
}