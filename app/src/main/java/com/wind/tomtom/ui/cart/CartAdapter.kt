package com.wind.tomtom.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wind.tomtom.R
import com.wind.tomtom.models.CartProduct
import com.wind.tomtom.repositories.CartRepository
import com.wind.tomtom.ui.BaseAdapter
import kotlinx.android.synthetic.main.item_cart.view.*
import kotlin.text.category

class CartAdapter(items: MutableList<CartProduct>) : BaseAdapter<CartProduct, CartViewHolder>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val viewHolder = CartViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cart,
                parent,
                false
            )
        )

        viewHolder.removeFromCart.setOnClickListener {
            CartRepository.removeCartItem(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.price.text = item.price.toString()
        holder.category.text = item.category
    }
}

class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image = view.productImage
    val name = view.productName
    val removeFromCart = view.removeFromCart
    val price = view.price
    val category = view.category
}