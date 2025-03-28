package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.ItemCategoryRectangularBinding
import com.mis.route.e_commerce.domain.model.Category

class CategoriesAdapter(
    private var data: List<Category> = emptyList(),
    val onCategoryClick: (Category) -> Unit
) :
    Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var selectedIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemCategoryRectangularBinding>(
            LayoutInflater.from(parent.context), R.layout.item_category_rectangular, parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = data[position]
        holder.binding.root.setOnClickListener {
            val clickedIndex = selectedIndex
            selectedIndex = position
            notifyItemChanged(selectedIndex)
            notifyItemChanged(clickedIndex)
            onCategoryClick(category)
        }
        holder.binding.category = category
        holder.binding.isSelected = position == selectedIndex
    }

    override fun getItemCount(): Int = data.size

    fun setCategories(categories: List<Category>) {
        data = categories
        notifyDataSetChanged()
    }

    class CategoryViewHolder(val binding: ItemCategoryRectangularBinding) : ViewHolder(binding.root)

}