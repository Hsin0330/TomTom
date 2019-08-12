package com.wind.tomtom.repositories

import android.util.Log
import com.wind.tomtom.data.Cart
import com.wind.tomtom.extensions.addTo
import com.wind.tomtom.models.CartProduct
import com.wind.tomtom.models.Product
import com.wind.tomtom.services.LocalCartService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

object CartRepository {

    private val compositeDisposable = CompositeDisposable()
    val cartUpdateSubject = BehaviorSubject.create<Cart>()

    private val cart = Cart()
    private val cartService = LocalCartService()

    fun clearAllDisposable() {
        compositeDisposable.clear()
    }

    fun addProductToCart(product: Product) {
        val cartProduct = CartProduct.fromProduct(product)

        cartService.addItemToCart(cartProduct)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cart.cartItems.add(cartProduct)
                cartUpdateSubject.onNext(cart)
            }, {
                Log.e("", "addProductToCart  " + it.message)
            }).addTo(compositeDisposable)
    }

    fun removeCartItem(cartProduct: CartProduct) {
        cartService.removeItemFromCart(cartProduct)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cart.cartItems.remove(cartProduct)
                cartUpdateSubject.onNext(cart)
            }, {
                Log.e("", "removeCartItem  " + it.message)
            }).addTo(compositeDisposable)
    }

    fun fetchTotalPrice(): Double {
        return cart.totalPrice
    }
}