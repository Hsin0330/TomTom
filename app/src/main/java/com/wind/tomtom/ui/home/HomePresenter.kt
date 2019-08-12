package com.wind.tomtom.ui.home

import com.wind.tomtom.extensions.addTo
import com.wind.tomtom.models.Product
import com.wind.tomtom.repositories.CartRepository
import com.wind.tomtom.services.ProductService
import com.wind.tomtom.ui.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeView, private val productService: ProductService, private val cartRepository: CartRepository) : BasePresenter() {

    fun addItemToCart(product: Product) {
        cartRepository.addProductToCart(product)
    }

    fun fetchCategories() {
        productService.fetchCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ categories -> view.fetchCategoriesSuccess(categories)},
                { throwable -> view.fetchCategoriesFailed(throwable)}).addTo(compositeDisposable)
    }

    fun fetchProducts() {
        productService.fetchProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ products -> view.fetchProductsSuccess(products.toMutableList())},
                { throwable -> view.fetchProductsFailed(throwable)}).addTo(compositeDisposable)
    }

    fun fetchProductsWithCategory(category: String) {
        productService.fetchProducts()
            .flatMapIterable { products -> products }
            .filter { product ->
                if (category == "All") {
                    true
                } else {
                    category == product.category
                }
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ products -> view.fetchProductsSuccess(products)},
                { throwable -> view.fetchProductsFailed(throwable)}).addTo(compositeDisposable)
    }
}

interface HomeView {
    fun fetchCategoriesSuccess(categories: List<String>)
    fun fetchCategoriesFailed(throwable: Throwable)
    fun fetchProductsSuccess(products: MutableList<Product>)
    fun fetchProductsFailed(throwable: Throwable)
}