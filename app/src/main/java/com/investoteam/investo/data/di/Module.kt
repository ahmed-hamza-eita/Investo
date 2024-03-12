package com.investoteam.investo.data.di

import android.app.Application
import android.content.Context
import com.investoteam.investo.data.datasoursce.datastore.UserPreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class Module {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }



}