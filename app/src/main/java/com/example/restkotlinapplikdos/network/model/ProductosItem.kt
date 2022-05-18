package com.example.restkotlinapplikdos.network.model

class Productos : ArrayList<ProductosItem>()

data class ProductosItem(
    val categoryId: Int,
    val description: String,
    val id: Int
)