package com.example.marvelgallery.data.networking


import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit by lazy { makeRetrofit() }
const val baseUrl: String = "https://gateway.marvel.com:443/v1/public/"

fun makeRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(makeHttpClient())
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()


fun makeHttpClient() = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .addInterceptor(makeHeadersInterceptor())
    .addInterceptor(makeAddSecurityQueryInterceptor())
    .addInterceptor(makeLoggingInterceptor())
    .build()
