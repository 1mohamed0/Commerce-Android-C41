package com.mis.route.e_commerce.domain.model


data class Product(
    val sold: Int = 0,
    val images: List<String?> = emptyList(),
    val quantity: Int = 0,
    //todo:Request this change from BE
    val availableColors: List<Any?> = emptyList(),
    val imageCover: String = "",
    val description: String = "",
    val title: String = "",
    val ratingsQuantity: Int = 0,
    val ratingsAverage: Double = 0.0,
    val price: Double = 0.0,
    val id: String = "",
    //todo:Request this change from BE
    val priceAfterDiscount: Double = 0.0,
    val category: Category
)