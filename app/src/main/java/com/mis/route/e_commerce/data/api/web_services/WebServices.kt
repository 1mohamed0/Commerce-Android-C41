package com.mis.route.e_commerce.data.api.web_services

import com.mis.route.e_commerce.domain.model.request.RegisterRequest
import com.mis.route.e_commerce.data.api.model.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

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
}
/// View -> ViewModel -> UseCases -> Repositories -> DataSource