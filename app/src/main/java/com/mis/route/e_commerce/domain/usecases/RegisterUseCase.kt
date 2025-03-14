package com.mis.route.e_commerce.domain.usecases

import com.mis.route.e_commerce.domain.model.request.RegisterRequest
import com.mis.route.e_commerce.domain.repositories.AuthRepository
import com.mis.route.e_commerce.domain.utils.ApiResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(var repo: AuthRepository) {
    suspend fun execute(registerRequest: RegisterRequest): ApiResult<Unit> {
        return repo.register(registerRequest)
    }
}