package com.mis.route.e_commerce.domain.repositories

import com.mis.route.e_commerce.domain.model.request.RegisterRequest
import com.mis.route.e_commerce.domain.utils.ApiResult

interface AuthRepository {
    suspend fun login(email: String, password: String): ApiResult<Unit>
    suspend fun register(registerRequest: RegisterRequest): ApiResult<Unit>
}