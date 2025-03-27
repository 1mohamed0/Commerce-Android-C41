package com.mis.route.e_commerce.data.api.web_services

import com.mis.route.e_commerce.data.api.model.response.TokenResponse
import com.mis.route.e_commerce.data.models.category.CategoriesResponse
import com.mis.route.e_commerce.data.models.product.ProductsResponse
import com.mis.route.e_commerce.domain.model.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WebServices {
    @FormUrlEncoded
    @POST("/api/v1/auth/signin")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): TokenResponse

    @POST("/api/v1/auth/signup")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): TokenResponse

    @GET("/api/v1/categories")
    suspend fun getCategories(): CategoriesResponse

    @GET("/api/v1/products")
    suspend fun getProducts(): ProductsResponse

    @GET("/api/v1/categories/{categoryId}/subcategories")
    suspend fun getSubCategories(@Path("categoryId") categoryId: String): CategoriesResponse
}
/// View -> ViewModel -> UseCases -> Repositories -> DataSource