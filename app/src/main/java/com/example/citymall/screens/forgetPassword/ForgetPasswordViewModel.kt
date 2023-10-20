package com.example.citymall.screens.forgetPassword

import androidx.lifecycle.ViewModel
import com.example.citymall.data.WrapperClass
import com.example.citymall.model.authentication.Authentication
import com.example.citymall.repository.MallRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(private val repository: MallRepository) :
    ViewModel() {

    suspend fun forgetPassword(email: String): WrapperClass<Authentication, Boolean, Exception> {
        return repository.forgetPassword(email = email)
    }

    suspend fun resetPassword(
        password: String,
        passwordConfirm: String,
        code: String
    ): WrapperClass<Authentication, Boolean, Exception> {
        return repository.resetPassword(
            password = password,
            code = code,
            passwordConfirm = passwordConfirm
        )
    }
}