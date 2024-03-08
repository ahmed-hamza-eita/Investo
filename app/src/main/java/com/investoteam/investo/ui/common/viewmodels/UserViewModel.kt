package com.investoteam.investo.ui.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.investoteam.investo.data.repository.UserDataStoreRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userDataStoreRepository: UserDataStoreRepositoryImpl) :
    ViewModel() {


    suspend fun isUserLoggedIn() = userDataStoreRepository.isUserLoggedIn()
    fun saveUserSate(isUserLoggedIn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            userDataStoreRepository.saveUserSate(isUserLoggedIn)
        }
    }

}

class UserViewModelFactory(private val userPreferencesRepository: UserDataStoreRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return UserViewModel(userPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}