package com.mis.route.e_commerce.domain.usecases

import com.mis.route.e_commerce.domain.model.Category
import com.mis.route.e_commerce.domain.repositories.HomeRepository
import com.mis.route.e_commerce.domain.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSubCategoriesOnCategoryUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    fun execute(categoryId: String): Flow<ApiResult<List<Category>>> =
        homeRepository.getSubCategories(categoryId)
}