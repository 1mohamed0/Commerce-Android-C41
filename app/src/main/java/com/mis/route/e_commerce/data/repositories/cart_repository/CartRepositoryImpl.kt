package com.mis.route.e_commerce.data.repositories.cart_repository

//class CartRepositoryImpl @Inject constructor(
//    private val cartRemoteDataSource: CartRemoteDataSource,
//    private val connectivity: ConnectivityChecker,
//    private val cartMapper: CartMapper
//) : CartRepository {
//    override fun getCart(): Flow<ApiResult<Cart>> {
//        return flow {
//            if (connectivity.isOnline()) {
//                when (val result = cartRemoteDataSource.getCart()) {
//                    is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(result.error)
//                    is ApiResult.SuccessApiResult -> {
//                        cartMapper.fromCartResponse(result.data!!)
//                    }
//                }
//            } else {
//                ApiResult.ErrorApiResult(AppErrors.NetworkError())
//            }
//        }
//    }
//
//    override fun addToCart(productId: String): Flow<ApiResult<Cart>> {
//        return flow {
//            if (connectivity.isOnline()) {
//                when (val result = cartRemoteDataSource.addToCart(productId)) {
//                    is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(result.error)
//                    is ApiResult.SuccessApiResult -> {
//                        cartMapper.fromCartResponse(result.data!!)
//                    }
//                }
//            } else {
//                ApiResult.ErrorApiResult(AppErrors.NetworkError())
//            }
//        }
//    }
//
//    override fun removeFromCart(productId: String): Flow<ApiResult<Cart>> {
//        return flow {
//
//            if (connectivity.isOnline()) {
//                when (val result = cartRemoteDataSource.removeFromCart(productId)) {
//                    is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(result.error)
//                    is ApiResult.SuccessApiResult -> {
//                        cartMapper.fromCartResponse(result.data!!)
//                    }
//                }
//            } else {
//                ApiResult.ErrorApiResult(AppErrors.NetworkError())
//            }
//
//        }
//    }
//
//    override fun updateCartQuantity(productId: String, quantity: Int): Flow<ApiResult<Cart>> {
//        return flow {
//
//            if (connectivity.isOnline()) {
//                when (val result =
//                    cartRemoteDataSource.updateProductQuantity(productId, quantity)) {
//                    is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(result.error)
//                    is ApiResult.SuccessApiResult -> {
//                        cartMapper.fromCartResponse(result.data!!)
//                    }
//                }
//            } else {
//                ApiResult.ErrorApiResult(AppErrors.NetworkError())
//            }
//
//        }
//    }

//}