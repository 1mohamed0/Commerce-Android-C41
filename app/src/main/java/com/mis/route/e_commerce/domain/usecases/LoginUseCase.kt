package com.mis.route.e_commerce.domain.usecases

import com.mis.route.e_commerce.domain.repositories.AuthRepository
import com.mis.route.e_commerce.domain.utils.ApiResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(var repo: AuthRepository) {
    suspend fun execute(email: String, password: String): ApiResult<Unit> {
        return repo.login(email, password)
    }
}