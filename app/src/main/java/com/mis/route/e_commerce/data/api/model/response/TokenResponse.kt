package com.mis.route.e_commerce.data.api.model.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("user")
    val user: User? = null,

    @field:SerializedName("token")
    val token: String? = null
)