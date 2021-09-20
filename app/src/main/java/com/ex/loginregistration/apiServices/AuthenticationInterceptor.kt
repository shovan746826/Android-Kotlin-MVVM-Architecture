package com.ex.loginregistration.apiServices

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor(token: String) : Interceptor {
    var authToken: String = token

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .header("Module", "JW9tc0ByZWRsdGQl")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("Authorization", authToken)
        val request = builder.build()
        return chain.proceed(request)
    }
}