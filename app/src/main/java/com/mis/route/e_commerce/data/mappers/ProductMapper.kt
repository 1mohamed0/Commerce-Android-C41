package com.mis.route.e_commerce.data.mappers

import com.mis.route.e_commerce.data.models.category.CategoryDM
import com.mis.route.e_commerce.data.models.product.ProductDM
import com.mis.route.e_commerce.domain.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor(private val categoryMapper: CategoryMapper) {
    fun fromDataModel(productDM: ProductDM): Product {
        return Product(
            sold = productDM.sold ?: 0,
            images = productDM.images ?: emptyList(),
            quantity = productDM.quantity ?: 0,
            availableColors = emptyList(), // Request change from BE for available colors
            imageCover = productDM.imageCover ?: "",
            description = productDM.description ?: "",
            title = productDM.title ?: "",
            ratingsQuantity = productDM.ratingsQuantity ?: 0,
            ratingsAverage = (productDM.ratingsAverage as? Double) ?: 0.0,
            price = (productDM.price?.toDouble()) ?: 0.0,
            id = productDM.id ?: "",
            priceAfterDiscount = 0.0, // Request change from BE for priceAfterDiscount
            category = categoryMapper.fromDataModel(productDM.category ?: CategoryDM())
        )
    }

    fun fromDataModels(models: List<ProductDM>): List<Product> {
        return models.map {
            fromDataModel(it)
        }
    }
}