package com.investoteam.investo.data.di

import android.app.Application
import android.content.Context
import com.investoteam.investo.data.datasoursce.api.ApiCalls
import com.investoteam.investo.data.datasoursce.datastore.UserPreferencesDataStore
import com.investoteam.investo.data.repository.UserDataStoreRepository
import com.investoteam.investo.utils.Const
import com.investoteam.investo.utils.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class Module {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideRetrofit(): ApiCalls = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Const.BASE_URL)
            .build().create(ApiCalls::class.java)
    }

