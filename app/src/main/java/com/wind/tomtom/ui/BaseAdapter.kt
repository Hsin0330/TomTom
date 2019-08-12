package com.wind.tomtom.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder>(val items: MutableList<T>) : RecyclerView.Adapter<VH>() {
    override fun getItemCount(): Int {
        return items.size
    }

    fun removeAll() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addAll(newItems: MutableList<T>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun replaceAll(newItems: MutableList<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}