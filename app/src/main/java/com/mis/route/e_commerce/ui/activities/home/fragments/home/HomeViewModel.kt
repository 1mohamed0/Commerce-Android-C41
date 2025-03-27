package com.mis.route.e_commerce.ui.activities.home.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mis.route.e_commerce.domain.model.Category
import com.mis.route.e_commerce.domain.model.Product
import com.mis.route.e_commerce.domain.usecases.GetCategoriesUseCase
import com.mis.route.e_commerce.domain.usecases.GetProductsUseCase
import com.mis.route.e_commerce.domain.utils.ApiResult
import com.mis.route.e_commerce.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    val categoriesApi = MutableLiveData<Resource<List<Category>>>(Resource.IdleState())
    val productsApi = MutableLiveData<Resource<List<Product>>>(Resource.IdleState())

    fun loadCategories() {
        categoriesApi.value = Resource.LoadingState()
        viewModelScope.launch {
            when (val result = getCategoriesUseCase.execute()) {
                is ApiResult.ErrorApiResult -> categoriesApi.postValue(Resource.ErrorState(result.error))
                is ApiResult.SuccessApiResult -> {
                    categoriesApi.postValue(Resource.SuccessState(result.data))
                }
            }
        }

    }

    fun loadProducts() {
        productsApi.value = Resource.LoadingState()
        viewModelScope.launch {
            when (val result = getProductsUseCase.execute()) {
                is ApiResult.ErrorApiResult -> productsApi.postValue(Resource.ErrorState(result.error))
                is ApiResult.SuccessApiResult -> {
                    productsApi.postValue(Resource.SuccessState(result.data))
                }
            }
        }
    }
}