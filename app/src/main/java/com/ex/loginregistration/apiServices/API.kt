package com.ex.loginregistration.apiServices

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("api/auth/login")
    fun login(@Body jsonObject: @JvmSuppressWildcards Map<String, Any>): Call<ResponseBody?>?
}