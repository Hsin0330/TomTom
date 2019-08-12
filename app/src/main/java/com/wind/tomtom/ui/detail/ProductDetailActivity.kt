package com.wind.tomtom.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wind.tomtom.R
import com.wind.tomtom.models.Product
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_PRODUCT = "product"

        fun createIntent(context: Context, product: Product): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(KEY_PRODUCT, product)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product: Product = intent!!.extras!!.getParcelable(KEY_PRODUCT)

        name.text = product.name
        price.text = product.price.toString()
        category.text = product.category
    }
}