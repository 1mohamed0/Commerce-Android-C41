package com.mis.route.e_commerce.data.repositories.auth_repository

import com.mis.route.e_commerce.data.repositories.auth_repository.datasources.auth_remote_data_source.AuthRemoteDataSource
import com.mis.route.e_commerce.data.utils.PrefsHelper
import com.mis.route.e_commerce.domain.di.ConnectivityChecker
import com.mis.route.e_commerce.domain.model.request.RegisterRequest
import com.mis.route.e_commerce.domain.repositories.AuthRepository
import com.mis.route.e_commerce.domain.utils.ApiResult
import com.mis.route.e_commerce.domain.utils.AppErrors
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val connectivityChecker: ConnectivityChecker,
    private val prefsHelper: PrefsHelper
) : AuthRepository {
    override suspend fun login(email: String, password: String): ApiResult<Unit> {
        return if (connectivityChecker.isOnline()) {
            when (val result = remoteDataSource.login(email, password)) {
                is ApiResult.ErrorApiResult -> {
                    ApiResult.ErrorApiResult(result.error)
                }

                is ApiResult.SuccessApiResult -> {
                    prefsHelper.saveToken(result.data?.token)
                    prefsHelper.saveUser(result.data?.user)
                    ApiResult.SuccessApiResult()
                }
            }
        } else {
            ApiResult.ErrorApiResult(AppErrors.NetworkError())
        }

    }

    override suspend fun register(registerRequest: RegisterRequest): ApiResult<Unit> {
        return if (connectivityChecker.isOnline()) {
            when (val result = remoteDataSource.register(registerRequest)) {
                is ApiResult.ErrorApiResult -> {
                    ApiResult.ErrorApiResult(result.error)
                }

                is ApiResult.SuccessApiResult -> {
                    prefsHelper.saveToken(result.data?.token)
                    prefsHelper.saveUser(result.data?.user)
                    ApiResult.SuccessApiResult()
                }
            }
        } else {
            ApiResult.ErrorApiResult(AppErrors.NetworkError())
        }
    }
}