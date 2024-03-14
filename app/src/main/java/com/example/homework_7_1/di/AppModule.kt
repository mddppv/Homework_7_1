package com.example.homework_7_1.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.homework_7_1.data.local.dao.AppDao
import com.example.homework_7_1.data.local.db.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppDao(database: AppDatabase): AppDao {
        return database.appDao()
    }
}