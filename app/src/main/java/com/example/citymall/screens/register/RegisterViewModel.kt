package com.example.citymall.screens.register

import androidx.lifecycle.ViewModel
import com.example.citymall.data.WrapperClass
import com.example.citymall.model.login.Login
import com.example.citymall.repository.MallRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: MallRepository) : ViewModel() {
    suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): WrapperClass<Login, Boolean, Exception> {
        return repository.register(
            name = name,
            phone = phone,
            email = email,
            password = password,
            passwordConfirm = passwordConfirm
        )
    }
}