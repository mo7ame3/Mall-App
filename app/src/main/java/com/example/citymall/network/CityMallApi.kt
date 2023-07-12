package com.example.citymall.network

import com.example.citymall.constant.Constant
import com.example.citymall.model.login.Login
import retrofit2.http.Body
import retrofit2.http.POST

interface CityMallApi {

    @POST(Constant.LOGIN)
    suspend fun login(
        @Body loginBody: Map<String, String>
    ): Login

    @POST(Constant.REGISTER)
    suspend fun register(
        @Body registerBody: Map<String, String>
    ): Login

    @POST(Constant.FORGET_PASSWORD)
    suspend fun forgetPassword(
        @Body forgetPasswordBody: Map<String, String>
    ): Login

    @POST(Constant.RESET_PASSWORD)
    suspend fun resetPassword(
        @Body resetPasswordBody: Map<String, String>
    ):Login

}