package com.wind.tomtom.services

import com.wind.tomtom.models.Product
import io.reactivex.Observable

interface ProductService {
    fun fetchProducts(): Observable<List<Product>>
    fun fetchCategories(): Observable<List<String>>
}