package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    //    private val _categoriesApi = MutableLiveData<Resource<List<Category>>>(Resource.IdleState())
//    val categoriesApi: LiveData<Resource<List<Category>>> = _categoriesApi
//    val subCategoriesApi = MutableLiveData<Resource<List<Category>>>(Resource.IdleState())
    private val _state = MutableLiveData(CategoriesEvents())
    val onEvent: LiveData<CategoriesEvents> = _state

    fun doAction(action: CategoriesActions) {
        when (action) {
            CategoriesActions.LoadCategories -> loadCategories()
            is CategoriesActions.LoadSubCategories -> loadSubCategories(action.categoryId)
        }
    }

    private fun loadCategories() {
        _state.value = CategoriesEvents(categoriesApi = Resource.LoadingState())
        viewModelScope.launch {
            when (val result = getCategoriesUseCase.execute()) {
                is ApiResult.ErrorApiResult ->
                    _state.value =
                        _state.value?.copy(categoriesApi = Resource.ErrorState(result.error))
                //_categoriesApi.postValue(Resource.ErrorState(result.error))
                is ApiResult.SuccessApiResult -> {
                    _state.value =
                        _state.value?.copy(categoriesApi = Resource.SuccessState(result.data))
                    // _categoriesApi.postValue(Resource.SuccessState(result.data))
                }
            }
        }

    }

    private fun loadSubCategories(categoryId: String) {
        //subCategoriesApi.value = Resource.LoadingState()
        _state.value = CategoriesEvents(subCategoriesApi = Resource.LoadingState())
        _state.value = onEvent.value?.copy(subCategoriesApi = Resource.LoadingState())
        viewModelScope.launch {
            when (val result = getSubCategoriesUseCase.execute(categoryId)) {
                is ApiResult.ErrorApiResult ->
                    _state.value =
                        _state.value?.copy(subCategoriesApi = Resource.ErrorState(result.error))
                //subCategoriesApi.postValue(Resource.ErrorState(result.error))
                is ApiResult.SuccessApiResult -> {
                    _state.value = CategoriesEvents(
                        subCategoriesApi = Resource.SuccessState(result.data)
                    )
                    _state.value =
                        _state.value?.copy(subCategoriesApi = Resource.SuccessState(result.data))
//                    subCategoriesApi.postValue(Resource.SuccessState(result.data))
                }
            }
        }
    }
}

sealed class CategoriesActions {
    object LoadCategories : CategoriesActions()
    class LoadSubCategories(val categoryId: String) : CategoriesActions()
}
