package com.investoteam.investo.data.di

import android.app.Application
import android.content.Context
import com.investoteam.investo.data.datasoursce.api.ApiCalls
import com.investoteam.investo.data.datasoursce.datastore.UserPreferencesDataStore
import com.investoteam.investo.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideRetrofit(): ApiCalls {
        val client = OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(150, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .callTimeout(50, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                val url = originalUrl.newBuilder().build()
                val requestBuilder = originalRequest.newBuilder().url(url)
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer ${MySharedPreferences.getUserToken()}"
                    )
                val request = requestBuilder.build()
                val response = chain.proceed(request)
                response.code//status code
                response
            })
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(Const.BASE_URL)
            .build().create(ApiCalls::class.java)
    }

}