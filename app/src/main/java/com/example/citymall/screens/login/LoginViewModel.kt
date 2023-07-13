package com.example.citymall.screens.login

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import com.example.citymall.data.WrapperClass
import com.example.citymall.model.authentication.Authentication
import com.example.citymall.repository.MallRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: MallRepository) : ViewModel() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun login(email : String, password : String):WrapperClass<Authentication , Boolean , Exception>{
        return repository.login(
            email = email , password = password
        )
    }
}