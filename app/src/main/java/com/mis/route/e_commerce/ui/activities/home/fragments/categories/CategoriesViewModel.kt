package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mis.route.e_commerce.domain.model.Category
import com.mis.route.e_commerce.domain.usecases.GetCategoriesUseCase
import com.mis.route.e_commerce.domain.usecases.GetSubCategoriesOnCategoryUseCase
import com.mis.route.e_commerce.domain.utils.ApiResult
import com.mis.route.e_commerce.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getSubCategoriesUseCase: GetSubCategoriesOnCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    val categoriesApi = MutableLiveData<Resource<List<Category>>>(Resource.IdleState())
    val subCategoriesApi = MutableLiveData<Resource<List<Category>>>(Resource.IdleState())

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

    fun loadSubCategories(categoryId: String) {
        subCategoriesApi.value = Resource.LoadingState()
        viewModelScope.launch {
            when (val result = getSubCategoriesUseCase.execute(categoryId)) {
                is ApiResult.ErrorApiResult -> subCategoriesApi.postValue(Resource.ErrorState(result.error))
                is ApiResult.SuccessApiResult -> {
                    subCategoriesApi.postValue(Resource.SuccessState(result.data))
                }
            }
        }
    }
}