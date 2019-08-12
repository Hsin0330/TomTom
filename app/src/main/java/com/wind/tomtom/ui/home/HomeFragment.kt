package com.wind.tomtom.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wind.tomtom.R
import com.wind.tomtom.models.Product
import com.wind.tomtom.repositories.CartRepository
import com.wind.tomtom.services.LocalProductService
import com.wind.tomtom.ui.cart.CartAdapter
import com.wind.tomtom.ui.cart.CartFragment
import com.wind.tomtom.ui.detail.ProductDetailActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeView {

    private val presenter = HomePresenter(this, LocalProductService(), CartRepository)

    private lateinit var productsAdapter: HomeAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun fetchCategoriesSuccess(categories: List<String>) {
        categoriesAdapter = CategoriesAdapter(context!!, categories)
        spinnerCategories.adapter = categoriesAdapter

        presenter.fetchProducts()
    }

    override fun fetchCategoriesFailed(throwable: Throwable) {
        Log.e(CartFragment::class.java.simpleName, "fetchCategoriesFailed  " + throwable.message)
    }

    override fun fetchProductsSuccess(products: MutableList<Product>) {
        productsAdapter.replaceAll(products)
    }

    override fun fetchProductsFailed(throwable: Throwable) {
        Log.e(CartFragment::class.java.simpleName, "fetchProductsFailed  " + throwable.message)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Do nothing
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.fetchProductsWithCategory(categoriesAdapter.getItem(position)!!)
            }
        }

        productsAdapter = HomeAdapter(mutableListOf())
        productList.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = productsAdapter
        }

        productsAdapter.onClickListener = { position ->
            context?.apply {
                startActivity(ProductDetailActivity.createIntent(this, productsAdapter.items[position]))
            }
        }

        presenter.fetchCategories()
    }

    override fun onStop() {
        super.onStop()
        presenter.disposed()
    }
}