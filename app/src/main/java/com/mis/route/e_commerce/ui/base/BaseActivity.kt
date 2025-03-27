package com.mis.route.e_commerce.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.domain.utils.AppErrors

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    var loadingDialog: AlertDialog? = null
    var errorDialog: AlertDialog? = null
    var binding: DB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    abstract fun getLayoutId(): Int
    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            loadingDialog = AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(R.layout.dialog_loading)
                .show()
            loadingDialog!!.show()
        } else {
            loadingDialog?.dismiss()
        }
    }

    fun handleError(error: AppErrors) {
        when (error) {
            is AppErrors.IgnoredErrors -> TODO()
            is AppErrors.LoginRequired -> TODO()
            is AppErrors.NetworkError -> {
                Toast.makeText(this, error.errorMessage, Toast.LENGTH_LONG).show()
            }

            is AppErrors.ServerError -> {
                errorDialog = AlertDialog.Builder(this)
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