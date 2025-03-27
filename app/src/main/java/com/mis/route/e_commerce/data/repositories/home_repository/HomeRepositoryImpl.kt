package com.mis.route.e_commerce.data.repositories.home_repository

import com.mis.route.e_commerce.data.mappers.CategoryMapper
import com.mis.route.e_commerce.data.mappers.ProductMapper
import com.mis.route.e_commerce.data.repositories.home_repository.dataSources.home_remote_datasource.HomeRemoteDataSource
import com.mis.route.e_commerce.domain.di.ConnectivityChecker
import com.mis.route.e_commerce.domain.model.Category
import com.mis.route.e_commerce.domain.model.Product
import com.mis.route.e_commerce.domain.repositories.HomeRepository
import com.mis.route.e_commerce.domain.utils.ApiResult
import com.mis.route.e_commerce.domain.utils.AppErrors
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeRemoteDataSource,
    private val connectivity: ConnectivityChecker,
    private val categoriesMapper: CategoryMapper,
    private val productMapper: ProductMapper
) : HomeRepository {
    override suspend fun getCategories(): ApiResult<List<Category>> {
        return if (connectivity.isOnline()) {
            when (val result = homeDataSource.getCategories()) {
                is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(result.error)
                is ApiResult.SuccessApiResult -> {
                    if (result.data?.categories.isNullOrEmpty()) {
                        ApiResult.ErrorApiResult(AppErrors.ServerError())
                    } else {
                        ApiResult.SuccessApiResult(categoriesMapper.fromDataModels(result.data?.categories!!))
                    }
                }
            }
        } else {
            ApiResult.ErrorApiResult(AppErrors.NetworkError())
        }
    }

    override suspend fun getSubCategories(categoryId: String): ApiResult<List<Category>> {
        return if (connectivity.isOnline()) {
            when (val result = homeDataSource.getSubCategories(categoryId)) {
                is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(result.error)
                is ApiResult.SuccessApiResult -> {
                    if (result.data?.categories.isNullOrEmpty()) {
                        ApiResult.ErrorApiResult(AppErrors.ServerError())
                    } else {
                        ApiResult.SuccessApiResult(categoriesMapper.fromDataModels(result.data?.categories!!))
                    }
                }
            }
        } else {
            ApiResult.ErrorApiResult(AppErrors.NetworkError())
        }
    }

    override suspend fun getProducts(): ApiResult<List<Product>> {
        return if (connectivity.isOnline()) {
            when (val result = homeDataSource.getProducts()) {
                is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(result.error)
                is ApiResult.SuccessApiResult -> {
                    if (result.data?.products.isNullOrEmpty()) {
                        ApiResult.ErrorApiResult(AppErrors.ServerError())
                    } else {
                        ApiResult.SuccessApiResult(productMapper.fromDataModels(result.data?.products!!))
                    }
                }
            }
        } else {
            ApiResult.ErrorApiResult(AppErrors.NetworkError())
        }
    }

}