package com.mis.route.e_commerce.data.repositories.auth_repository.datasources.auth_remote_data_source

import com.mis.route.e_commerce.data.api.model.response.TokenResponse
import com.mis.route.e_commerce.domain.model.request.RegisterRequest
import com.mis.route.e_commerce.domain.utils.ApiResult

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): ApiResult<TokenResponse>
    suspend fun register(registerRequest: RegisterRequest): ApiResult<TokenResponse>
}