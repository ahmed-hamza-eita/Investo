package com.investoteam.investo.ui.auth.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.investoteam.investo.data.datasoursce.network.NetworkState
import com.investoteam.investo.data.repository.auth.RegisterRepository
import com.investoteam.investo.data.datasoursce.network.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {
    private val _registerLiveData = SingleLiveEvent<NetworkState>()
    val registerLiveData get() = _registerLiveData
    fun signup(
        fName: String,
        lName: String,
        email: String,
        password: String,
        nationalId: String,
        gender: String,
        birthday: String
    ) {
        _registerLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch {
            try {
                val data = repository.signup(
                    fName,
                    lName,
                    email,
                    password,
                    nationalId,
                    gender, birthday
                )
                if (data.status == 1) {
                    _registerLiveData.postValue(NetworkState.getLoaded(data))
                } else {
                    _registerLiveData.postValue(NetworkState.getErrorMessage(data.message))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _registerLiveData.postValue(NetworkState.getErrorMessage(e.message.toString()))
            }

        }
    }
}