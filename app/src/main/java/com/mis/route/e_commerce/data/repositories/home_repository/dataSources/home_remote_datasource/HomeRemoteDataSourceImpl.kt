package com.mis.route.e_commerce.data.repositories.home_repository.dataSources.home_remote_datasource

import com.mis.route.e_commerce.data.api.web_services.WebServices
import com.mis.route.e_commerce.data.models.category.CategoriesResponse
import com.mis.route.e_commerce.data.models.product.ProductsResponse
import com.mis.route.e_commerce.data.utils.safeApiCall
import com.mis.route.e_commerce.domain.utils.ApiResult
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject
constructor(private val webServices: WebServices) : HomeRemoteDataSource {
    override suspend fun getCategories(): ApiResult<CategoriesResponse> {
        return safeApiCall {
            webServices.getCategories()
        }
    }

    override suspend fun getSubCategories(categoryId: String): ApiResult<CategoriesResponse> {
        return safeApiCall {
            webServices.getSubCategories(categoryId)
        }
    }

    override suspend fun getProducts(): ApiResult<ProductsResponse> =
        safeApiCall { webServices.getProducts() }
}

