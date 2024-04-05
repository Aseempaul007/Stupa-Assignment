package com.example.stupa.di

import android.content.Context
import androidx.room.Room
import com.example.stupa.data.local.UserDb
import com.example.stupa.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    //dependency for room database builder
    @Provides
    @Singleton
    fun provideRoomInstance(@ApplicationContext context: Context): UserDb {
        synchronized(this) {
            return Room.databaseBuilder(
                context,
                UserDb::class.java,
                "student_db"
            )
                .build()
        }
    }

    //dependency for room database builder
    @Provides
    fun providesStudentDao(userDb: UserDb): UserDao {
        return userDb.dao()
    }
}