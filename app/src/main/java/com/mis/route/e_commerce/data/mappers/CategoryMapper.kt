package com.mis.route.e_commerce.data.mappers

import com.mis.route.e_commerce.data.models.category.CategoryDM
import com.mis.route.e_commerce.domain.model.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun fromDataModel(model: CategoryDM): Category =
        Category(id = model.id ?: "", name = model.name ?: "Unkown", image = model.image ?: "")

    fun fromDataModels(models: List<CategoryDM>): List<Category> {
        return models.map {
            fromDataModel(it)
        }
    }
}
/// Hilt will face a problem with these types of classes:
//1 - create object from interface or abstract
//2 - 3rd part packages
//3 - Creating a custom constructor
