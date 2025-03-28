package com.mis.route.e_commerce.ui.activities.home.fragments.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mis.route.e_commerce.databinding.ItemProductBinding
import com.mis.route.e_commerce.domain.model.Product

class ProductsRecyclerAdapter(var productsList: List<Product?>?, var onClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductsRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product?) {
            binding.product = product
            binding.root.setOnClickListener {
                product?.let {
                    onClick.invoke(product)
                }
            }
            binding.productCover.setBackgroundColor(Color.WHITE)
            binding.addToFavorites.isVisible = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = productsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productsList!![position]
        holder.bind(product)
        // set click listener later
    }
}