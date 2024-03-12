package com.investoteam.investo.data.di

import com.investoteam.investo.data.repository.UserDataStoreRepository
import com.investoteam.investo.data.repository.UserDataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUserDataStoreRepository(impl: UserDataStoreRepositoryImpl): UserDataStoreRepository
}
