package com.wind.tomtom.services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wind.tomtom.models.Product
import io.reactivex.Observable

class LocalProductService : ProductService {
    override fun fetchProducts(): Observable<List<Product>> {
        return Observable.just(Gson().fromJson(fakeProductList(), object : TypeToken<ArrayList<Product>>(){ }.type))
    }

    override fun fetchCategories(): Observable<List<String>> {
        return Observable.just(arrayListOf("All", "Food", "Soft Drink"))
    }

    fun fakeProductList(): String {
        return "[\n" +
                "    {\n" +
                "        Product: \"Potato Chips\",\n" +
                "        Price: 10.0,\n" +
                "        Category: \"Food\"\n" +
                "    },\n" +
                "    {\n" +
                "        Product: \"Chocolate\",\n" +
                "        Price: 15.0,\n" +
                "        Category: \"Food\"\n" +
                "    },\n" +
                "    {\n" +
                "        Product: \"Italian Sauce\",\n" +
                "        Price: 50.0,\n" +
                "        Category: \"Food\"\n" +
                "    },\n" +
                "    {\n" +
                "        Product: \"Corn Chips\",\n" +
                "        Price: 15.0,\n" +
                "        Category: \"Food\"\n" +
                "    },\n" +
                "    {\n" +
                "        Product: \"Beer\",\n" +
                "        Price: 20.0,\n" +
                "        Category: \"Soft Drink\"\n" +
                "    },\n" +
                "    {\n" +
                "        Product: \"Cola\",\n" +
                "        Price: 15.0,\n" +
                "        Category: \"Soft Drink\"\n" +
                "    },\n" +
                "    {\n" +
                "        Product: \"Orange Juice\",\n" +
                "        Price: 13.0,\n" +
                "        Category: \"Soft Drink\"\n" +
                "    }\n" +
                "]"
    }
}