package com.example.restkotlinapplikdos.network

import com.example.restkotlinapplikdos.network.model.ProductosItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET(value = "productos")
    fun getAllProductosService(): Call<List<ProductosItem>>

    @GET(value = "productos/{id}")
    fun getProductosByIdService(@Path(value = "id") id:Int) : Call<ProductosItem?>?
}