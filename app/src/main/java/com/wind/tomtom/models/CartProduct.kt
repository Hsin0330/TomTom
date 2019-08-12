package com.wind.tomtom.models


data class CartProduct(val name: String,
                       val price: Double,
                       val category: String,
                       val imageUrl: String?) {

    companion object {
        fun fromProduct(product: Product): CartProduct {
            return CartProduct(product.name, product.price, product.category, product.imageUrl)
        }
    }
}