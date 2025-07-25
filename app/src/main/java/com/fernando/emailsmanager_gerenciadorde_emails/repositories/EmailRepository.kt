package com.fernando.emailsmanager_gerenciadorde_emails.repositories

import com.fernando.emailsmanager_gerenciadorde_emails.mappers.toModelEntity
import com.fernando.emailsmanager_gerenciadorde_emails.mappers.toRoomEntity
import com.fernando.emailsmanager_gerenciadorde_emails.models.Email
import com.fernando.emailsmanager_gerenciadorde_emails.room.daos.EmailEntityDAO
import javax.inject.Inject

class EmailRepository @Inject constructor(private val emailEntityDAO: EmailEntityDAO) {
    suspend fun findAllEmails() : List<Email> {
        return emailEntityDAO.findAllEmails()
            .map { it.toModelEntity() }
    }

    suspend fun saveEmail(email: Email) {
        val emailToSave = email.toRoomEntity()
        emailEntityDAO.saveEmail(emailToSave)
    }

    suspend fun existsEmailByName(name: String) : Boolean {
        return emailEntityDAO.findEmailByName(name) != null
    }

    suspend fun existsEmail(email: String) : Boolean {
        return emailEntityDAO.findEmail(email) != null
    }

    suspend fun findEmailByID(emailID: String) : Email {
        val email = emailEntityDAO.findEmailByID(emailID)
        return email.toModelEntity()
    }

    suspend fun deleteEmailByID(emailID: String) {
        emailEntityDAO.deleteEmailByID(emailID)
    }

    suspend fun updateEmail(newEmailValue: String, emailID: String) : Email {
        val email = emailEntityDAO.findEmailByID(emailID).toModelEntity()
        email.updateEmailValue(newEmailValue)

        emailEntityDAO.updateEmail(newEmailValue, emailID)
        return email
    }

    suspend fun updateEmailName(newEmailName: String, emailID: String) : Email {
        val email = emailEntityDAO.findEmailByID(emailID).toModelEntity()
        email.updateEmailName(newEmailName)

        emailEntityDAO.updateEmailName(newEmailName, emailID)
        return email
    }


}