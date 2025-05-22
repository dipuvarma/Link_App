package com.example.linkapp.utils

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error<out T>(val error: String) : Response<T>()
}