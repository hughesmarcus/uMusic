package com.umusic.marcus.umusic.data.remote.retrofit


import com.umusic.marcus.umusic.data.Constants.ACCESS_TOKEN
import com.umusic.marcus.umusic.data.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class ApiInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val request = original.newBuilder().header(API_KEY, ACCESS_TOKEN).method(original.method(), original.body()).build()
        return chain.proceed(request)
    }
}