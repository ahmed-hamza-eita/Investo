package com.investoteam.investo.data.models


import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Logged in successfully , Please check your email for verification.
    @SerializedName("status")
    val status: Int // 200
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: String, // 2024-03-13T14:27:08.000000Z
        @SerializedName("dob")
        val dob: String, // 2002-2-2
        @SerializedName("email")
        val email: String, // reda12gg@gmail.com
        @SerializedName("f_name")
        val fName: String, // ebrahim
        @SerializedName("gender")
        val gender: String, // male
        @SerializedName("l_name")
        val lName: String, // reda
        @SerializedName("national_id")
        val nationalId: String, // 232323232323
        @SerializedName("updated_at")
        val updatedAt: String // 2024-03-13T14:27:08.000000Z
    )
}