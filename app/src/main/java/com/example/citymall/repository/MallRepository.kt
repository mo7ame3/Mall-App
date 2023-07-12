package com.example.citymall.repository

import retrofit2.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.citymall.data.WrapperClass
import com.example.citymall.model.login.Login
import com.example.citymall.network.CityMallApi
import javax.inject.Inject

class MallRepository @Inject constructor(private val api: CityMallApi) {

    private val login: WrapperClass<Login, Boolean, Exception> = WrapperClass()
    private val register: WrapperClass<Login, Boolean, Exception> = WrapperClass()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun login(
        email: String,
        password: String
    ): WrapperClass<Login, Boolean, Exception> {
        try {
            login.data = api.login(
                loginBody = mapOf(
                    "email" to email,
                    "password" to password
                )
            )
        } catch (e: HttpException) {
            val error = e.response()?.errorBody()?.string()
            val status = error!!.split("status")[1].split(":")[1].split("\"")[1]
            val message = error.split("message")[1].split("\":")[1]
            login.data = Login(status = status, message = message)
        } catch (e: Exception) {
            Log.d("TAG", "login: $e")
            login.e = e
        }
        return login
    }


    suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): WrapperClass<Login, Boolean, Exception> {
        try {
            register.data = api.register(
                registerBody = mapOf(
                    "name" to name,
                    "phone" to phone,
                    "email" to email,
                    "password" to password,
                    "passwordConfirm" to passwordConfirm
                )
            )
        } catch (e: HttpException) {
            val error = e.response()?.errorBody()?.string()
            val status = error!!.split("status")[1].split(":")[1].split("\"")[1]
            val message = error.split("message")[1].split("\":")[1]
            register.data = Login(status = status, message = message)
        } catch (e: Exception) {
            Log.d("TAG", "register: $e")
            register.e = e
        }
        return register
    }

}