package com.bmnmrls.domain

import com.bmnmrls.domain.Failure

sealed class DataState<out R> {

    object Loading : DataState<Nothing>()

    object Empty : DataState<Nothing>()

    data class Error(val error: Failure) : DataState<Nothing>()

    data class Success<out T>(val data: T) : DataState<T>()

}