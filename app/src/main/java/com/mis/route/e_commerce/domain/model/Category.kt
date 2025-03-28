package com.mis.route.e_commerce.domain.model

import java.io.Serializable

data class Category(
    val id: String = "",
    val image: String = "",
    val name: String = ""
) : Serializable