package com.vuoncog.converter.ui.models

import com.vuoncog.data.models.ErrorTag

sealed class RequestState<out T>{
    object Idle: RequestState<Nothing>()
    object Loading: RequestState<Nothing>()
    data class Error(val error: ErrorTag): RequestState<Nothing>()
    data class Success<T>(val data: T): RequestState<T>()
}