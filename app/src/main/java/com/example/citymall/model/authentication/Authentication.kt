package com.example.citymall.model.authentication

data class Authentication(
    val data: Data? = null,
    val status: String,
    val token: String? = null,
    val message: String
)