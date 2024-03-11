package com.investoteam.investo.data.datasoursce.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class UserPreferencesDataStore(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    suspend fun saveUserLoggedInState(isUserLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_USER_LOGGED_IN] = isUserLoggedIn
        }
    }

    suspend fun saveUserToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.USER_TOKEN] = token
        }
    }

    val isUserLoggedIn = context.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.IS_USER_LOGGED_IN] ?: false
    }

    val userToken = context.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.USER_TOKEN]
    }
}