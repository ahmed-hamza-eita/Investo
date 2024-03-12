package com.investoteam.investo.ui.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.investoteam.investo.data.repository.UserDataStoreRepository
import com.investoteam.investo.data.repository.UserDataStoreRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userDataStoreRepository: UserDataStoreRepository) :
    ViewModel() {


    suspend fun isUserLoggedIn() = userDataStoreRepository.isUserLoggedIn()
    fun saveUserSate(isUserLoggedIn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            userDataStoreRepository.saveUserSate(isUserLoggedIn)
        }
    }

}


