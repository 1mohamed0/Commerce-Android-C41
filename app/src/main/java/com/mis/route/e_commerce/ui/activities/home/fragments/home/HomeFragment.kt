package com.mis.route.e_commerce.ui.activities.home.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mis.route.domain.models.category.Category
import com.mis.route.domain.models.offer.Offer
import com.mis.route.domain.models.product.Product
import com.mis.route.domain.models.subcategory.SubCategory
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentHomeBinding
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.CategoriesRecyclerAdapter
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.OfferViewPagerAdapter
import com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter.ProductsRecyclerAdapter
import com.mis.route.e_commerce.ui.utils.UIConstants.whenViewIsShown
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // stop injection for now
    private var categoriesAdapter = CategoriesRecyclerAdapter(null)
    private var productsAdapter = ProductsRecyclerAdapter(null)
    private var isProductsAlreadyVisible = false
    private var offersAdapter = OfferViewPagerAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOffersViewPager()
        initCategoriesRecyclerView()
        initProductsRecyclerView()
    }


    private fun bindOffers(offersList: List<Offer>?) {
        if (offersList.isNullOrEmpty()) return
        offersAdapter.offersList = offersList
        offersAdapter.notifyDataSetChanged()

    }

    private fun bindCategories(categoriesList: List<Category?>?) {
        if (categoriesList.isNullOrEmpty()) return
        // TODO: loading is delayed for debug purposes only, this MUST be removed from release versions
        Handler(Looper.getMainLooper()).postDelayed({
            binding.categoriesRecyclerViewShimmer.stopShimmer()
            binding.categoriesRecyclerViewShimmer.visibility = View.INVISIBLE
            categoriesAdapter.categoriesList = categoriesList
            categoriesAdapter.notifyDataSetChanged()
        }, 1000)
    }

    private fun bindProducts(productsList: List<Product?>?) {
        if (productsList.isNullOrEmpty()) return
        // TODO: loading is delayed for debug purposes only, this MUST be removed from release versions
        Handler(Looper.getMainLooper()).postDelayed({
            binding.lapsAccessoriesRecyclerViewShimmer.stopShimmer()
            binding.lapsAccessoriesRecyclerViewShimmer.visibility = View.INVISIBLE
            productsAdapter.productsList = productsList
            productsAdapter.notifyDataSetChanged()
        }, 1000)
    }

    private fun initProductsRecyclerView() {
        binding.root.whenViewIsShown(
            binding.lapsAccessoriesRecyclerViewShimmer,
            ::startLoadingProducts
        )
        binding.lapsAccessoriesRecyclerView.adapter = productsAdapter
    }

    private fun startLoadingProducts() {
        if (isProductsAlreadyVisible) return

        bindProducts(DummyDataProvider.getProducts())
        isProductsAlreadyVisible = true
    }

    private fun initCategoriesRecyclerView() {
        binding.categoriesRecyclerView.adapter = categoriesAdapter
        bindCategories(DummyDataProvider.getCategories())
    }

    private fun initOffersViewPager() {
        binding.offersViewPager.adapter = offersAdapter
        TabLayoutMediator(binding.tabLayout, binding.offersViewPager) { tab, position ->
            //Some implementation
        }.attach()

        bindOffers(DummyDataProvider.getOffers())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.lapsAccessoriesRecyclerViewShimmer.stopShimmer()
        binding.categoriesRecyclerViewShimmer.stopShimmer()
        _binding = null
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

    fun getCategories(): List<Category> {
        return listOf(
            Category(
                id = null,
                name = "Music",
                slug = "music",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511964020.jpeg",
                createdAt = "2023-04-14T22:39:24.365Z",
                updatedAt = "2023-04-14T22:39:24.365Z"
            ),
            Category(
                id = null,
                name = "Men's Fashion",
                slug = "men's-fashion",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511865180.jpeg",
                createdAt = "2023-04-14T22:37:45.627Z",
                updatedAt = "2023-04-14T22:37:45.627Z"
            ),
            Category(
                id = null,
                name = "Women's Fashion",
                slug = "women's-fashion",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511818071.jpeg",
                createdAt = "2023-04-14T22:36:58.118Z",
                updatedAt = "2023-04-14T22:36:58.118Z"
            ),
            Category(
                id = null,
                name = "SuperMarket",
                slug = "supermarket",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511452254.png",
                createdAt = "2023-04-14T22:30:52.311Z",
                updatedAt = "2023-04-14T22:30:52.311Z"
            ),
            Category(
                id = null,
                name = "Baby & Toys",
                slug = "baby-and-toys",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511427130.png",
                createdAt = "2023-04-14T22:30:27.166Z",
                updatedAt = "2023-04-14T22:30:27.166Z"
            ),
            Category(
                id = null,
                name = "Home",
                slug = "home",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511392672.png",
                createdAt = "2023-04-14T22:29:52.763Z",
                updatedAt = "2023-04-14T22:29:52.763Z"
            ),
            Category(
                id = null,
                name = "Books",
                slug = "books",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511368164.png",
                createdAt = "2023-04-14T22:29:28.200Z",
                updatedAt = "2023-04-14T22:29:28.200Z"
            ),
            Category(
                id = null,
                name = "Beauty & Health",
                slug = "beauty-and-health",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511179514.png",
                createdAt = "2023-04-14T22:26:19.587Z",
                updatedAt = "2023-04-14T22:26:19.587Z"
            ),
            Category(
                id = null,
                name = "Mobiles",
                slug = "mobiles",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511156008.png",
                createdAt = "2023-04-14T22:25:56.071Z",
                updatedAt = "2023-04-14T22:25:56.071Z"
            ),
            Category(
                id = null,
                name = "Electronics",
                slug = "electronics",
                image = "https://ecommerce.routemisr.com/Route-Academy-categories/1681511121316.png",
                createdAt = "2023-04-14T22:25:21.783Z",
                updatedAt = "2023-04-14T22:25:21.783Z"
            )
        )

    }

    fun getProducts(): List<Product> {
        return listOf(
            Product(
                sold = 4519,
                images = listOf(
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403397482-1.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403397482-2.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403397483-3.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403397485-4.jpeg"
                ),
                subcategory = listOf(
                    SubCategory(
                        id = "6407f1bcb575d3b90bf95797",
                        name = "Women's Clothing",
                        slug = "women's-clothing",
                        category = "6439d58a0049ad0b52b9003f"
                    )
                ),
                ratingsQuantity = 18,
                id = "6428ebc6dc1175abc65ca0b9",
                title = "Woman Shawl",
                slug = "woman-shawl",
                description = "Material\tPolyester Blend\nColour Name\tMulticolour\nDepartment\tWomen",
                quantity = 225,
                price = 160,
                imageCover = "https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747398/Route-Academy-products/1680403397402-cover.jpeg",
                category = Category(
                    id = "6439d58a0049ad0b52b9003f",
                    name = "Women's Fashion",
                    slug = "women's-fashion",
                    image = "https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747343/Route-Academy-categories/1681511818071.jpeg"
                ),
                ratingsAverage = 4.8,
                createdAt = "2023-04-02T02:43:18.400Z",
                updatedAt = "2024-04-01T12:41:53.020Z"
            ),

            Product(
                sold = 7819,
                images = listOf(
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403266805-1.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403266806-3.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403266806-2.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403266807-4.jpeg"
                ),
                subcategory = listOf(
                    SubCategory(
                        id = "6407f1bcb575d3b90bf95797",
                        name = "Women's Clothing",
                        slug = "women's-clothing",
                        category = "6439d58a0049ad0b52b9003f"
                    )
                ),
                ratingsQuantity = 18,
                id = "6428eb43dc1175abc65ca0b3",
                title = "Woman Shawl",
                slug = "woman-shawl",
                description = "Material\tPolyester Blend\nColour Name\tMulticolour\nDepartment\tWomen",
                quantity = 220,
                price = 149,
                imageCover = "https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747398/Route-Academy-products/1680403266739-cover.jpeg",
                category = Category(
                    id = "6439d58a0049ad0b52b9003f",
                    name = "Women's Fashion",
                    slug = "women's-fashion",
                    image = "https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747343/Route-Academy-categories/1681511818071.jpeg"
                ),
                ratingsAverage = 4.8,
                createdAt = "2023-04-02T02:41:07.506Z",
                updatedAt = "2024-04-01T12:28:03.463Z"
            ),

            Product(
                sold = 8909,
                images = listOf(
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403156555-3.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403156555-2.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403156554-1.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680403156556-4.jpeg"
                ),
                subcategory = listOf(
                    SubCategory(
                        id = "6407f1bcb575d3b90bf95797",
                        name = "Women's Clothing",
                        slug = "women's-clothing",
                        category = "6439d58a0049ad0b52b9003f"
                    )
                ),
                ratingsQuantity = 18,
                id = "6428ead5dc1175abc65ca0ad",
                title = "Woman Shawl",
                slug = "woman-shawl",
                description = "Material\tPolyester Blend\nColour Name\tMulticolour\nDepartment\tWomen",
                quantity = 220,
                price = 149,
                imageCover = "https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747398/Route-Academy-products/1680403156501-cover.jpeg",
                category = Category(
                    id = "6439d58a0049ad0b52b9003f",
                    name = "Women's Fashion",
                    slug = "women's-fashion",
                    image = "https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747343/Route-Academy-categories/1681511818071.jpeg"
                ),
                ratingsAverage = 4.8,
                createdAt = "2023-04-02T02:39:17.204Z",
                updatedAt = "2024-04-01T12:28:03.463Z",
            ),
            Product(
                sold = 5976,
                images = listOf(
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680402838330-1.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680402838331-3.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680402838332-4.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680402838331-2.jpeg",
                    "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680402838332-5.jpeg"
                ),
                subcategory = listOf(
                    SubCategory(
                        id = "6407f1bcb575d3b90bf95797",
                        name = "Women's Clothing",
                        slug = "women's-clothing",
                        category = "6439d58a0049ad0b52b9003f"
                    )
                ),
                ratingsQuantity = 18,
                id = "6428e997dc1175abc65ca0a1",
                title = "Woman Shawl",
                slug = "woman-shawl",
                description = "Material\tPolyester Blend\nColour Name\tMulticolour\nDepartment\tWomen",
                quantity = 220,
                price = 149,
                imageCover = "https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747398/Route-Academy-products/1680402838276-cover.jpeg",
                category = Category(
                    id = "6439d58a0049ad0b52b9003f",
                    name = "Women's Fashion",
                    slug = "women's-fashion",
                    image = "https://res.cloudinary.com/dwp0imlbj/image/upload/v1680747343/Route-Academy-categories/1681511818071.jpeg"
                ),
                ratingsAverage = 4.8,
                createdAt = "2023-04-02T02:33:59.274Z",
                updatedAt = "2024-04-01T07:50:14.998Z",
            )
        )

    }

}
