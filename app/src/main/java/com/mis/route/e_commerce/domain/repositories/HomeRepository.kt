package com.mis.route.e_commerce.domain.repositories

import com.mis.route.e_commerce.domain.model.Category
import com.mis.route.e_commerce.domain.model.Product
import com.mis.route.e_commerce.domain.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getCategories(): ApiResult<List<Category>>
    fun getSubCategories(categoryId: String): Flow<ApiResult<List<Category>>>
    suspend fun getProducts(): ApiResult<List<Product>>

}