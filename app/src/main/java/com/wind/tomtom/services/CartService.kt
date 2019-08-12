package com.wind.tomtom.services

import com.wind.tomtom.models.CartProduct
import io.reactivex.Completable
import io.reactivex.Single

interface CartService {
    fun addItemToCart(product: CartProduct): Completable
    fun removeItemFromCart(product: CartProduct) : Single<CartProduct>
}