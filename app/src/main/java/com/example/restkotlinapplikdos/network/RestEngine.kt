package com.example.restkotlinapplikdos.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RestEngine {

    companion object {
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun getRetrofit(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://apimocha.com/cucuapi/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

            return retrofit
        }
    }
}
/*
Con setLevel indicamos el tipo de tracking que vamos a hacer con las peticiones
 */