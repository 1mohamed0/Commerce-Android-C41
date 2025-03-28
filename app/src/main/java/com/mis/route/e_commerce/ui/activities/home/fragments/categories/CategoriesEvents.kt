package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import com.mis.route.e_commerce.domain.model.Category
import com.mis.route.e_commerce.ui.utils.Resource

data class CategoriesEvents(
    val categoriesApi: Resource<List<Category>> = Resource.IdleState(),
    val subCategoriesApi: Resource<List<Category>> = Resource.IdleState()
) {
//    fun copyWith(
//        categoriesApi: Resource<List<Category>> = this.categoriesApi,
//        subCategoriesApi: Resource<List<Category>> = this.subCategoriesApi
//    ): CategoriesState = CategoriesState(
//        categoriesApi = categoriesApi,
//        subCategoriesApi = subCategoriesApi
//    )
}