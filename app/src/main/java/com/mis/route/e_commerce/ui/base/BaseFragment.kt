package com.mis.route.e_commerce.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.domain.utils.AppErrors

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    var loadingDialog: AlertDialog? = null
    var errorDialog: AlertDialog? = null
    var binding: Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, getLayoutId(),
            container,
            false
        )
        return binding!!.root
    }

    abstract fun getLayoutId(): Int
    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            loadingDialog = AlertDialog.Builder(requireContext())
                .setCancelable(false)
                .setView(R.layout.dialog_loading)
                .show()
        } else {
            loadingDialog?.dismiss()
        }
    }

    fun handleError(error: AppErrors) {
        when (error) {
            is AppErrors.IgnoredErrors -> TODO()
            is AppErrors.LoginRequired -> TODO()
            is AppErrors.NetworkError -> {
                Toast.makeText(requireContext(), error.errorMessage, Toast.LENGTH_LONG).show()
            }

            is AppErrors.ServerError -> {
                errorDialog = AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage(error.errorMessage)
                    .setPositiveButton("Ok", { dialog, _ ->
                        dialog.dismiss()
                    })
                    .show()
            }
        }
    }
}