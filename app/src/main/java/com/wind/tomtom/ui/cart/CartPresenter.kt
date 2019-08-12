package com.wind.tomtom.ui.cart

import android.util.Log
import com.wind.tomtom.data.Cart
import com.wind.tomtom.extensions.addTo
import com.wind.tomtom.repositories.CartRepository
import com.wind.tomtom.ui.BasePresenter

class CartPresenter(private val view: CartView, private val cartRepository: CartRepository) : BasePresenter() {
    fun subscribeCartUpdate() {
        cartRepository.cartUpdateSubject
            .subscribe({ cart ->
                view.onCartUpdated(cart)
            }, { throwable ->
                Log.e(CartPresenter::class.java.simpleName, "subscribeCartUpdate  " + throwable.message)
            }).addTo(compositeDisposable)
    }
}

interface CartView {
    fun onCartUpdated(cart: Cart)
}