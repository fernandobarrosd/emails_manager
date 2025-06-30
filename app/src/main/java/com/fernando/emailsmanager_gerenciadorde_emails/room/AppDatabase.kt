package com.fernando.emailsmanager_gerenciadorde_emails.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fernando.emailsmanager_gerenciadorde_emails.room.daos.EmailEntityDAO
import com.fernando.emailsmanager_gerenciadorde_emails.room.entities.EmailEntity

@Database(entities = [EmailEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emailEntityDao() : EmailEntityDAO
}