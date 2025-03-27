package com.mis.route.e_commerce.data.repositories.home_repository.dataSources.home_remote_datasource

import com.mis.route.e_commerce.data.models.category.CategoriesResponse
import com.mis.route.e_commerce.data.models.product.ProductsResponse
import com.mis.route.e_commerce.domain.utils.ApiResult

interface HomeRemoteDataSource {
    suspend fun getCategories(): ApiResult<CategoriesResponse>

    suspend fun getSubCategories(categoryId: String): ApiResult<CategoriesResponse>
    suspend fun getProducts(): ApiResult<ProductsResponse>
}