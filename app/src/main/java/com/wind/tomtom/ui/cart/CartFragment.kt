package com.wind.tomtom.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wind.tomtom.R
import com.wind.tomtom.data.Cart
import com.wind.tomtom.repositories.CartRepository
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.productList

class CartFragment : Fragment(), CartView {

    private lateinit var productsAdapter: CartAdapter
    private val presenter = CartPresenter(this, CartRepository)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter = CartAdapter(mutableListOf())
        productList.apply {
            layoutManager = LinearLayoutManager(this@CartFragment.context)
            adapter = productsAdapter
        }

        presenter.subscribeCartUpdate()
    }

    override fun onCartUpdated(cart: Cart) {
        productsAdapter.replaceAll(cart.cartItems)
        totalPrice.text = getString(R.string.total_price, cart.totalPrice.toString())
    }

    override fun onStop() {
        super.onStop()
        presenter.disposed()
    }
}