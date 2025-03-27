package com.mis.route.e_commerce.ui.activities.home.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mis.route.domain.models.offer.Offer
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentHomeBinding
import com.mis.route.e_commerce.domain.model.Product
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.CategoriesRecyclerAdapter
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.OfferViewPagerAdapter
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.ProductsRecyclerAdapter
import com.mis.route.e_commerce.ui.base.BaseFragment
import com.mis.route.e_commerce.ui.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeVM by viewModels<HomeViewModel>()

    // stop injection for now
    private var categoriesAdapter = CategoriesRecyclerAdapter(null)
    private var productsAdapter = ProductsRecyclerAdapter(null)
    private var isProductsAlreadyVisible = false
    private var offersAdapter = OfferViewPagerAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeVM.loadProducts()
        homeVM.loadCategories()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        initOffersViewPager()
        initCategoriesRecyclerView()
        initProductsRecyclerView()
    }

    private fun setUpObservers() {
        homeVM.categoriesApi.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.ErrorState -> handleError(it.error)
                is Resource.IdleState -> TODO()
                is Resource.LoadingState -> {
                    binding!!.categoriesRecyclerViewShimmer.startShimmer()
                    binding!!.categoriesRecyclerViewShimmer.visibility = View.VISIBLE
                }

                is Resource.SuccessState -> {
                    binding!!.categoriesRecyclerViewShimmer.stopShimmer()
                    binding!!.categoriesRecyclerViewShimmer.visibility = View.INVISIBLE
                    categoriesAdapter.categoriesList = it.data
                    categoriesAdapter.notifyDataSetChanged()
                }
            }
        }
        homeVM.productsApi.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.ErrorState -> handleError(it.error)
                is Resource.IdleState -> TODO()
                is Resource.LoadingState -> {
                    binding!!.lapsAccessoriesRecyclerViewShimmer.startShimmer()
                    binding!!.lapsAccessoriesRecyclerViewShimmer.visibility = View.VISIBLE
                }

                is Resource.SuccessState -> bindProducts(it.data)
            }
        }
    }


    private fun bindOffers(offersList: List<Offer>?) {
        if (offersList.isNullOrEmpty()) return
        offersAdapter.offersList = offersList
        offersAdapter.notifyDataSetChanged()

    }


    private fun bindProducts(productsList: List<Product>?) {
        binding!!.lapsAccessoriesRecyclerViewShimmer.stopShimmer()
        binding!!.lapsAccessoriesRecyclerViewShimmer.visibility = View.INVISIBLE
        productsAdapter.productsList = productsList
        productsAdapter.notifyDataSetChanged()
    }

    private fun initProductsRecyclerView() {
//        binding!!.root.whenViewIsShown(
//            binding!!.lapsAccessoriesRecyclerViewShimmer,
//            ::startLoadingProducts
//        )
        binding!!.lapsAccessoriesRecyclerView.adapter = productsAdapter
    }

    private fun startLoadingProducts() {
        if (isProductsAlreadyVisible) return

        //   bindProducts(DummyDataProvider.getProducts())
        isProductsAlreadyVisible = true
    }

    private fun initCategoriesRecyclerView() {
        binding!!.categoriesRecyclerView.adapter = categoriesAdapter
        //  bindCategories(DummyDataProvider.getCategories())
    }

    private fun initOffersViewPager() {
        binding!!.offersViewPager.adapter = offersAdapter
        TabLayoutMediator(binding!!.tabLayout, binding!!.offersViewPager) { tab, position ->
            //Some implementation
        }.attach()

        bindOffers(DummyDataProvider.getOffers())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding!!.lapsAccessoriesRecyclerViewShimmer.stopShimmer()
        binding!!.categoriesRecyclerViewShimmer.stopShimmer()
    }
}


object DummyDataProvider {
    fun getOffers(): List<Offer> {
        return listOf(
            Offer(1, 25, R.drawable.image_headset, "1", "For all Headphones \n& AirPods"),
            Offer(2, 30, R.drawable.image_beauty_products, "2", "For all Makeup\n& Skincare"),
            Offer(3, 20, R.drawable.image_laptop, "3", "For Laptops\n& Mobiles")
        )
    }
}
