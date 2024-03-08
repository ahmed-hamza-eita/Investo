package com.investoteam.investo.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.investoteam.investo.data.datasoursce.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.investoteam.investo.data.datasoursce.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreRepositoryImpl(private val context: Context) : UserDataStoreRepository {
    override suspend fun isUserLoggedIn(): Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[IS_USER_LOGGED_IN] ?: false
        }

    override suspend fun saveUserSate(isUserLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN] = isUserLoggedIn
        }
    }
}