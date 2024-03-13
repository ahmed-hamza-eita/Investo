package com.investoteam.investo.data.datasoursce.api

import com.investoteam.investo.data.models.UserModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiCalls {

    @FormUrlEncoded
    @POST("register")
    suspend fun signup(
        @Field("f_name") fName: String,
        @Field("l_name") lName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("national_id") nationalId: String,
        @Field("gender") gender: String,
        @Field("DOB") birthday: String,

    ): UserModel
}