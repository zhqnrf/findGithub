package com.example.findgithub.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInstance: ApiUser by lazy {
        retrofit.create(ApiUser::class.java)
    }
}


//object RetrofitClient {
//    private const val BASE_URL = "https://api.github.com/"
//
//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val apiInstance: ApiUser by lazy {
//        retrofit.create(ApiUser::class.java)
//    }
//}

