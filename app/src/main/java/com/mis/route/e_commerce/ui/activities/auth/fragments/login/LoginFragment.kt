package com.mis.route.e_commerce.ui.activities.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentLoginBinding
import com.mis.route.e_commerce.ui.activities.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.donTHaveAnAccountTv.setOnClickListener { navigateToRegister() }
        binding.loginBtn.setOnClickListener { fakeLogin() }
    }

    private fun fakeLogin() {
        startActivity(Intent(requireContext(), HomeActivity::class.java))
        requireActivity().finish()
    }

    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}