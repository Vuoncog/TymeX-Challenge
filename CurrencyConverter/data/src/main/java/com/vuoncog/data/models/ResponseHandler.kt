package com.vuoncog.data.models

import android.content.Context
import com.vuoncog.data.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

enum class ErrorTag {
    CONNECTIVITY,
    SERVER,
    UNKNOWN;

    companion object {
        fun ErrorTag.errorTagToMessage(context: Context) = when (this) {
            CONNECTIVITY -> context.getString(R.string.network_error)
            SERVER -> context.getString(R.string.server_error)
            UNKNOWN -> context.getString(R.string.unknown_error)
        }
    }
}

enum class STATUS {
    SUCCESS,
    ERROR
}

data class ResponseHandler<T>(
    val status: STATUS,
    val data: T?,
    val error: ErrorTag?,
) {
    companion object {
        fun <T> success(data: T) = ResponseHandler(
            status = STATUS.SUCCESS,
            data = data,
            error = null
        )

        fun <T> error(e: Exception) = when (e) {
            is HttpException, is SSLHandshakeException -> ResponseHandler<T>(
                status = STATUS.ERROR,
                data = null,
                error = ErrorTag.SERVER
            )

            is UnknownHostException, is ConnectException -> ResponseHandler<T>(
                status = STATUS.ERROR,
                data = null,
                error = ErrorTag.CONNECTIVITY
            )

            else -> ResponseHandler<T>(
                status = STATUS.ERROR,
                data = null,
                error = ErrorTag.UNKNOWN
            )
        }
    }
}