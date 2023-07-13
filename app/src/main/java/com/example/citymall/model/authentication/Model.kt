package com.example.citymall.model.authentication

data class Model(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val passwordConfirm: Any,
    val phone: Int,
    val updatedAt: String,
    val createdAt: String,
    val passwordChangedAt: String,
    val passwordResetCode: String,
    val passwordResetExpire: String,
    val imageUrl: String
)