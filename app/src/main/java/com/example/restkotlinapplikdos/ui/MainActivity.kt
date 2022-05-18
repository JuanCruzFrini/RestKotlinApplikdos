package com.example.restkotlinapplikdos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restkotlinapplikdos.databinding.ActivityMainBinding
import com.example.restkotlinapplikdos.domain.ProductosAdapter
import com.example.restkotlinapplikdos.network.model.ProductosItem
import com.example.restkotlinapplikdos.network.UserService
import com.example.restkotlinapplikdos.network.RestEngine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: ProductosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.btnRestGetAll.setOnClickListener { callServiceGetAllProductos()  }
        binding.btnRestById.setOnClickListener { callServiceGetById() }
    }

    private fun getAllProductos(listaProductos:  List<ProductosItem>) {
        adapter = ProductosAdapter(listaProductos)
        binding.rvProdcutos.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun callServiceGetAllProductos() {
        val userService: UserService = RestEngine.getRetrofit().create(UserService::class.java)
        val result: Call<List<ProductosItem>> = userService.getAllProductosService()

        result.enqueue(object : Callback<List<ProductosItem>>{
            override fun onResponse(call: Call<List<ProductosItem>>, response: Response<List<ProductosItem>>) {
                getAllProductos(iterarList(response))
                Toast.makeText(applicationContext, "Recibido", Toast.LENGTH_LONG).show()
            }
            override fun onFailure(call: Call<List<ProductosItem>>, t: Throwable) {
                Toast.makeText(applicationContext, "FAllido ${t.toString()}", Toast.LENGTH_LONG).show()
                println( "FAllido ${t.toString()}")
            }
        })
    }

    private fun callServiceGetById(){
        val userService = RestEngine.getRetrofit().create(UserService::class.java)
        val result:Call<ProductosItem?>? = userService.getProductosByIdService(1)

        result?.enqueue(object  : Callback<ProductosItem?> {
            override fun onResponse(call: Call<ProductosItem?>, response: Response<ProductosItem?>) {
                getProductoById(iterarListById(response))
            }

            override fun onFailure(call: Call<ProductosItem?>, t: Throwable) {
                Toast.makeText(applicationContext, "Error ${t.message.toString()}", Toast.LENGTH_SHORT).show()
                println("Error ${t.message.toString()}")
            }
        })
    }

    private fun getProductoById(producto: List<ProductosItem>) {
        adapter = ProductosAdapter(producto)
        binding.rvProdcutos.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    private val listProductos = ArrayList<ProductosItem>()
    fun iterarList(response:Response<List<ProductosItem>>) : List<ProductosItem>{
        listProductos.clear()
        for (i in response.body()!!){
            listProductos.add(ProductosItem(id = i.id, description = i.description, categoryId = i.categoryId))
            println("Producto = ${i.description}, ${i.id}, ${i.categoryId}")
        }
        return listProductos
    }

    fun iterarListById(response:Response<ProductosItem?>) : List<ProductosItem>{
        listProductos.clear()
        //for (i in response.body()!!){
            listProductos.add(ProductosItem(id = response.body()!!.id, description = response.body()!!.description, categoryId = response.body()!!.categoryId))
            println("Producto = ${response.body()!!.description}, ${response.body()!!.id}, ${response.body()!!.categoryId}")
        //}
        return listProductos
    }

}