package com.wind.tomtom.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wind.tomtom.R
import com.wind.tomtom.models.Product
import com.wind.tomtom.repositories.CartRepository
import com.wind.tomtom.ui.BaseAdapter
import kotlinx.android.synthetic.main.item_product.view.*

class HomeAdapter(items: MutableList<Product>) : BaseAdapter<Product, HomeViewHolder>(items) {

    var onClickListener = { _: Int ->}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val viewHolder = HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )
        viewHolder.addToCart.setOnClickListener {
            CartRepository.addProductToCart(items[viewHolder.adapterPosition])
        }

        viewHolder.itemView.setOnClickListener {
            onClickListener.invoke(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holderHome: HomeViewHolder, position: Int) {
        val item = items[position]
        holderHome.name.text = item.name
        holderHome.price.text = item.price.toString()
        holderHome.category.text = item.category
    }
}

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image = view.productImage
    val name = view.productName
    val addToCart = view.addToCart
    val price = view.price
    val category = view.category
}