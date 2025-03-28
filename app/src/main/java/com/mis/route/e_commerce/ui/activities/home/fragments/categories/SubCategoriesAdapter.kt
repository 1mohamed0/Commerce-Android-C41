package com.mis.route.e_commerce.ui.activities.home.fragments.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.ItemSubcategoryBinding
import com.mis.route.e_commerce.domain.model.Category

class SubCategoriesAdapter(
    private var data: List<Category> = emptyList(),
    val onCategoryClick: (Category) -> Unit
) :
    Adapter<SubCategoriesAdapter.SubCategoiresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoiresViewHolder {
        val binding = DataBindingUtil.inflate<ItemSubcategoryBinding>(
            LayoutInflater.from(parent.context), R.layout.item_subcategory, parent, false
        )
        return SubCategoiresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubCategoiresViewHolder, position: Int) {
        val category = data[position]
        holder.binding.root.setOnClickListener {
            onCategoryClick(category)
        }
        holder.binding.subCategory = category
    }

    override fun getItemCount(): Int = data.size

    fun setSubCategories(categories: List<Category>) {
        data = categories
        notifyDataSetChanged()
    }

    class SubCategoiresViewHolder(val binding: ItemSubcategoryBinding) : ViewHolder(binding.root)

}