package com.wind.tomtom.services

import com.wind.tomtom.models.CartProduct
import io.reactivex.Completable
import io.reactivex.Single

class LocalCartService : CartService {
    override fun addItemToCart(product: CartProduct): Completable {
        return Completable.fromAction {
            //TODO use local DB or prefence
        }
    }

    override fun removeItemFromCart(product: CartProduct): Single<CartProduct> {
        return Single.fromCallable {
            //TODO use local DB or prefence
            product
        }
    }
}