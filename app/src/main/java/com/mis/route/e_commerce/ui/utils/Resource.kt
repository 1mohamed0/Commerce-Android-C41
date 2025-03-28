package com.mis.route.e_commerce.ui.utils

import com.mis.route.e_commerce.domain.utils.AppErrors

sealed class Resource<out T> {
    data object LoadingState : Resource<Nothing>()
    class IdleState<E> : Resource<E>()
    class SuccessState<X>(var data: X? = null) : Resource<X>()
    class ErrorState<Y>(val error: AppErrors) : Resource<Y>()
}
