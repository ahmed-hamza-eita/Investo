package com.investoteam.investo.data.repository.auth

import com.investoteam.investo.data.datasoursce.api.ApiCalls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val apiCalls: ApiCalls)  {
      suspend fun signup(
        fName: String,
        lName: String,
        email: String,
        password: String,
        nationalId: String,
        gender: String,
        birthday: String
    ) = withContext(Dispatchers.IO) {
        apiCalls.signup(fName, lName, email, password, nationalId, gender, birthday)
    }
}