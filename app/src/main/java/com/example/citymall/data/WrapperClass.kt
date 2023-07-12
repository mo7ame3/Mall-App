package com.example.citymall.data

class WrapperClass<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null,
)