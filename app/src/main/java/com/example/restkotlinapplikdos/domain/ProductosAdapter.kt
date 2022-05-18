package com.example.restkotlinapplikdos.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restkotlinapplikdos.R
import com.example.restkotlinapplikdos.network.model.ProductosItem

class ProductosAdapter(private val listaProductos : List<ProductosItem>) : RecyclerView.Adapter<ProductosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = listaProductos[position].id.toString()
        holder.description.text = listaProductos[position].description.toString()
        holder.categoryId.text = listaProductos[position].categoryId.toString()
    }

    override fun getItemCount(): Int = listaProductos.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id = itemView.findViewById<TextView>(R.id.txtId)
        val description = itemView.findViewById<TextView>(R.id.txtDescription)
        val categoryId = itemView.findViewById<TextView>(R.id.txtCategoryId)
    }
}