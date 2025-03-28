package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentCategoriesBinding
import com.mis.route.e_commerce.domain.model.Category
import com.mis.route.e_commerce.ui.base.BaseFragment
import com.mis.route.e_commerce.ui.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var subCategoriesAdapter: SubCategoriesAdapter
    private val categoriesVM by viewModels<CategoriesViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_categories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        categoriesVM.loadCategories()
        categoriesVM.doAction(CategoriesActions.LoadCategories)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        initCategoriesRecyclerView()
        initSubCategoriesRecyclerView()

    }

    private fun initSubCategoriesRecyclerView() {
        subCategoriesAdapter = SubCategoriesAdapter {

        }
        binding!!.subcategoryRv.adapter = subCategoriesAdapter
    }

    private fun initCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter {
//            categoriesVM.loadSubCategories()
            categoriesVM.doAction(CategoriesActions.LoadSubCategories(it.id))
        }
        binding!!.categoriesRv.adapter = categoriesAdapter
    }

    private fun setUpObservers() {
        categoriesVM.onEvent.observe(viewLifecycleOwner) {
            when (it.categoriesApi) {
                is Resource.ErrorState -> handleError(it.categoriesApi.error)
                is Resource.SuccessState -> bindCategories(it.categoriesApi.data)
                else -> {}
            }
            when (it.subCategoriesApi) {
                is Resource.ErrorState -> handleError(it.subCategoriesApi.error)
                is Resource.SuccessState -> bindSubCategories(it.subCategoriesApi.data)
                else -> {}
            }
        }
    }

    private fun bindSubCategories(data: List<Category>?) {
        data?.let {
            subCategoriesAdapter.setSubCategories(it)
        }
    }

    private fun bindCategories(categories: List<Category>?) {
        binding!!.categoriesShimmerViewContainer.stopShimmer()
        categories?.let {
            categoriesAdapter.setCategories(it)
            if (!(categoriesVM.onEvent.value?.subCategoriesApi is Resource.SuccessState
                        || categoriesVM.onEvent.value?.subCategoriesApi is Resource.LoadingState)
            ) {
//
                categoriesVM.doAction(CategoriesActions.LoadSubCategories(categories[0].id))
            }

        }
    }

}