package com.ex.loginregistration.apiServices

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServices {
    companion object{

        private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        @Synchronized
        private fun builder(token: String): Retrofit {
            val interceptor = AuthenticationInterceptor(token)
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(interceptor)
            httpClient.addInterceptor(logging)
            return Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(API_URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        @Synchronized
        fun getApi(token: String): API? {
            return builder(token).create(API::class.java)
        }
    }

}