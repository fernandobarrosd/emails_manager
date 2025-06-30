package com.fernando.emailsmanager_gerenciadorde_emails.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "email_table")
data class EmailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String = "",
    val email: String = ""
)