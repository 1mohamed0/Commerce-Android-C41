package com.mis.route.e_commerce.domain.utils

sealed class ApiResult<T>(var data: T? = null) {

    class SuccessApiResult<T>(data: T? = null) : ApiResult<T>(data)
    class ErrorApiResult<T>(var error: AppErrors) : ApiResult<T>(null)
}