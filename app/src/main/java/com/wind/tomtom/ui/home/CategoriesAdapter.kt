package com.wind.tomtom.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.wind.tomtom.R
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(context: Context, items: List<String>) : ArrayAdapter<String>(context, 0, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: CategoryViewHolder

        if (convertView != null) {
            view = convertView
            holder = convertView.tag as CategoryViewHolder
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
            holder = CategoryViewHolder(view)

            view.tag = holder
        }

        val category = getItem(position)
        holder.category.text = category
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}

class CategoryViewHolder(view: View) {
    val category = view.category
}