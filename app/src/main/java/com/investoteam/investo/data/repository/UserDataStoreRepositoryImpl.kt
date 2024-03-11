package com.investoteam.investo.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.investoteam.investo.data.datasoursce.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.investoteam.investo.data.datasoursce.datastore.UserPreferencesDataStore
import com.investoteam.investo.data.datasoursce.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreRepositoryImpl(private val userPreferencesDataStore: UserPreferencesDataStore) :
    UserDataStoreRepository {
    override suspend fun isUserLoggedIn(): Flow<Boolean> = userPreferencesDataStore.isUserLoggedIn

    override suspend fun saveUserSate(isUserLoggedIn: Boolean) {
        userPreferencesDataStore.saveUserLoggedInState(isUserLoggedIn)
    }

    override suspend fun saveUserToken(token: String) {
        userPreferencesDataStore.saveUserToken(token)
    }

    override fun getUserToken(): Flow<String?> =
        userPreferencesDataStore.userToken


}