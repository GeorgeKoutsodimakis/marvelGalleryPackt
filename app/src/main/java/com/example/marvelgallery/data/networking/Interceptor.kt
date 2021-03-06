package com.example.marvelgallery.data.networking

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import com.example.marvelgallery.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import javax.crypto.Cipher.PUBLIC_KEY


fun makeHeadersInterceptor() = Interceptor { chain -> // 1
    chain.proceed(chain.request().newBuilder()
        .addHeader("Accept", "application/json")
        .addHeader("Accept-Language", "en")
        .addHeader("Content-Type", "application/json")
        .build())
}


fun makeAddSecurityQueryInterceptor() = Interceptor { chain ->
    val originalRequest = chain.request()
    val timeStamp = System.currentTimeMillis()

    // Url customization: add query parameters
    val url = originalRequest.url.newBuilder()
        .addQueryParameter("apikey", "519bab83fed8d6962c976b57118db50c")
        .addQueryParameter("ts", "$timeStamp")
        .addQueryParameter("hash", calculatedMd5(timeStamp.toString() + "9deb6c61644111ec559b3f49ef08b8b57e1a6b54" + "519bab83fed8d6962c976b57118db50c"))

        .build()

    // Request customization: set custom url
    val request = originalRequest
        .newBuilder()
        .url(url)
        .build()

    chain.proceed(request)
}

fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
}

/**
 * Calculate MD5 hash for text
 * @param timeStamp Current timeStamp
 * @return MD5 hash string
 */
fun calculatedMd5(text: String): String {
    val messageDigest = getMd5Digest(text)
    val md5 = BigInteger(1, messageDigest).toString(16)
    return "0" * (32 - md5.length) + md5 // 3
}

private fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray())

private operator fun String.times(i: Int) = (1..i).fold("") { acc, _ -> acc + this }