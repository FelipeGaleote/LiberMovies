package com.example.libermovies.repository

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


fun newRetrofitInstance(): Retrofit {
    val httpClient = OkHttpClient.Builder()

    val gson = GsonBuilder().setLenient().create()

    return Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build()
}


