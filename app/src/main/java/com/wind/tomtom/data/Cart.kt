package com.wind.tomtom.data

import com.wind.tomtom.models.CartProduct

class Cart {
    val cartItems = mutableListOf<CartProduct>()

    val totalPrice : Double
        get() {
            var price = 0.0
            for(product in cartItems) {
                price += product.price
            }

            return price
        }

}