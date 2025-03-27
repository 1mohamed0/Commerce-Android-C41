package com.mis.route.e_commerce.data.utils

import com.mis.route.e_commerce.domain.utils.ApiResult

suspend fun <T> safeApiCall(call: suspend () -> T): ApiResult<T> {
    return try {
        val response = call()
        ApiResult.SuccessApiResult(response)
    } catch (e: Throwable) {
        handleError(e)
    }
}