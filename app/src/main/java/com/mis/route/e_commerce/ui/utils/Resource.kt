package com.mis.route.e_commerce.ui.utils

import com.mis.route.e_commerce.domain.utils.AppErrors

sealed class Resource<T> {
    class LoadingState<E> : Resource<E>()
    class IdleState<E> : Resource<E>()
    class SuccessState<X>(var data: X? = null) : Resource<X>()
    class ErrorState<Y>(val error: AppErrors) : Resource<Y>()
}
