package com.mis.route.e_commerce.ui.activities.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentLoginBinding
import com.mis.route.e_commerce.ui.activities.home.HomeActivity
import com.mis.route.e_commerce.ui.base.BaseFragment
import com.mis.route.e_commerce.ui.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


///DataBinding:
///1- U can add simple if conditions in xml
///2- U can call methods directly in xml
///3- I can use 2 data binding(from view model to xml and vice versa)
///4- U can add binding adapters
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val loginViewModel by viewModels<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding!!.root
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.viewModel = loginViewModel
        binding!!.lifecycleOwner = this
        initListeners()
        setupObservers()
    }

    private fun setupObservers() {
        loginViewModel.loginApiState.observe(viewLifecycleOwner) {
            showLoading(it is Resource.LoadingState)
            when (it) {
                is Resource.ErrorState -> handleError(it.error)
                is Resource.SuccessState -> startHomeActivity()
                else -> {}
            }
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }

    private fun initListeners() {
//        binding.loginBtn.setOnClickListener {
        ///This validation is replaced with binding adapter
//            if(true){
//                binding.emailTextInput.error = "Please enter valid email"
//            }
//        }
//        binding.emailEdt.addTextChangedListener(object :TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                 loginViewModel.email = p0
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
//            }
//
//        })
        // This part will be replaced in login xml
//        binding.loginBtn.setOnClickListener {
//            loginViewModel.login();
//        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}