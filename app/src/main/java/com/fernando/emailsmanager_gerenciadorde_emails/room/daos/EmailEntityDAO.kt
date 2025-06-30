package com.fernando.emailsmanager_gerenciadorde_emails.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fernando.emailsmanager_gerenciadorde_emails.room.entities.EmailEntity

@Dao
interface EmailEntityDAO {
    @Insert
    fun saveEmail(email: EmailEntity)

    @Query("SELECT * FROM email_table")
    suspend fun findAllEmails() : List<EmailEntity>

    @Query("SELECT * FROM email_table WHERE id = :emailID")
    suspend fun findEmailByID(emailID: String) : EmailEntity

    @Query("DELETE FROM email_table WHERE id = :emailID")
    suspend fun deleteEmailByID(emailID: String)

    @Query("UPDATE email_table SET email = :newEmailValue WHERE id = :emailID")
    suspend fun updateEmail(newEmailValue: String, emailID: String)

    @Query("UPDATE email_table SET name = :newEmailName WHERE id = :emailID")
    suspend fun updateEmailName(newEmailName: String, emailID: String)
}