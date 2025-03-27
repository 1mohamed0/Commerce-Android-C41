package com.mis.route.e_commerce.ui.activities.auth.fragments.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mis.route.e_commerce.domain.usecases.LoginUseCase
import com.mis.route.e_commerce.domain.utils.ApiResult
import com.mis.route.e_commerce.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    val loginApiState = MutableLiveData<Resource<Unit>>(Resource.IdleState())
    var emailLiveData = MutableLiveData("")
    var passwordLiveData = MutableLiveData("")
    var emailError = MutableLiveData("")
    var passwordError = MutableLiveData("")
    fun login() {
        loginApiState.value = Resource.LoadingState()
        viewModelScope.launch {
            when (val result = loginUseCase.execute(
                emailLiveData.value!!,
                passwordLiveData.value!!
            )) {
                is ApiResult.ErrorApiResult -> {
                    Log.e("LoginViewModel", "ErrorApiResult -> ${result.error}")
                    loginApiState.postValue(Resource.ErrorState(result.error))
                }

                is ApiResult.SuccessApiResult -> {
                    Log.e("LoginViewModel", "SuccessApiResult")
                    loginApiState.postValue(Resource.SuccessState())
                }
            }
        }
    }
}