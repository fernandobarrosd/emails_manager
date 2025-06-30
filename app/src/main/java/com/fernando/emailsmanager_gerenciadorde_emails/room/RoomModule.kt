package com.fernando.emailsmanager_gerenciadorde_emails.room

import android.content.Context
import androidx.room.Room
import com.fernando.emailsmanager_gerenciadorde_emails.room.daos.EmailEntityDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun emailEntityDAO(appDatabase: AppDatabase) : EmailEntityDAO {
        return appDatabase.emailEntityDao()
    }
}