package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentCategoriesBinding
import com.mis.route.e_commerce.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    private val categoriesVM by viewModels<CategoriesViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_categories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesVM.loadCategories()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()

    }

    private fun setUpObservers() {
        categoriesVM.categoriesApi.observe(viewLifecycleOwner) {

        }
    }

}