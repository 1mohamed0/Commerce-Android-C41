package com.mis.route.e_commerce.ui.activities.home.fragments.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mis.route.e_commerce.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_details, container, false)
    }
}