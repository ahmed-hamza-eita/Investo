package com.investoteam.investo.data.repository

import kotlinx.coroutines.flow.Flow

interface UserDataStoreRepository {

    suspend fun isUserLoggedIn(): Flow<Boolean>
    suspend fun saveUserSate(isUserLoggedIn: Boolean)

    suspend fun saveUserToken(token: String)
    fun getUserToken(): Flow<String?>
}