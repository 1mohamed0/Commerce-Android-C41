package com.mis.route.e_commerce.ui.activities.auth.fragments.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentLoginBinding
import com.mis.route.e_commerce.domain.utils.AppErrors
import com.mis.route.e_commerce.ui.activities.home.HomeActivity
import com.mis.route.e_commerce.ui.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


///DataBinding:
///1- U can add simple if conditions in xml
///2- U can call methods directly in xml
///3- I can use 2 data binding(from view model to xml and vice versa)
///4- U can add binding adapters
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val loginViewModel by viewModels<LoginViewModel>()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
        initListeners()
        setupObservers()
    }

    private fun setupObservers() {
        loginViewModel.loginApiState.observe(viewLifecycleOwner) {
            showLoading(it is Resource.LoadingState)
            when (it) {
                is Resource.ErrorState -> showError(it.error)
                is Resource.SuccessState -> startHomeActivity()
                else -> {}
            }
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            dialog = ProgressDialog(requireContext())
            dialog!!.show()
        } else {
            dialog?.dismiss()
        }
    }

    var dialog: ProgressDialog? = null
    private fun showError(error: AppErrors) {
        Toast.makeText(requireContext(), error.errorMessage, Toast.LENGTH_LONG)
            .show()
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
        _binding = null
    }
}