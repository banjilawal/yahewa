package com.lawal.banji.yahewa.repo

sealed class QueryResult<out T> {
        data class Success<out T>(val data: T) : QueryResult<T>()
        data class Error(val exception: Exception) : QueryResult<Nothing>()
}