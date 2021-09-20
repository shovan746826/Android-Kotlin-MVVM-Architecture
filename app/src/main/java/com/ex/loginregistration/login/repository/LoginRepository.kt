package com.ex.loginregistration.login.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.ex.loginregistration.apiServices.APIServices
import com.ex.loginregistration.login.model.LoginRepositoryModel
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

@Suppress("NAME_SHADOWING")
class LoginRepository {

    var livedataLoginRepository: MutableLiveData<LoginRepositoryModel> = MutableLiveData()


    fun getLoginResponse(number: String, password: String): MutableLiveData<LoginRepositoryModel> {
        val jsonObject = HashMap<String, Any>()
        jsonObject["mobile"] = number
        jsonObject["password"] = password
        jsonObject["imei"] = ""
        jsonObject["phone_brand"] = ""
        jsonObject["phone_os"] = ""
        jsonObject["os_version"] = ""
        jsonObject["device_id"] = ""
        jsonObject["fcm"] = ""

        APIServices.getApi("")?.login(jsonObject)?.enqueue(object : Callback<ResponseBody?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) {
                    try {
                        assert(response.body() != null)
                        val res = response.body()!!.string()
                        res.let {
                            val jsonObject = JSONObject(it)
                            val is_success = jsonObject.getBoolean("issuccess")
                            val jsonObjectPayload = jsonObject.getJSONObject("payload")
                            val jsonObjectUserData = jsonObjectPayload.getJSONObject("user_info")
                            val token = jsonObjectPayload.getString("token")
                            val user_first_name = jsonObjectUserData.getString("firstname")
                            val user_last_name = jsonObjectUserData.getString("lastname")
                            val user_email = jsonObjectUserData.getString("email")
                            val user_number = jsonObjectUserData.getString("mobile")
                            val user_reg_id = jsonObjectUserData.getString("idx")
                            val isparentcustomer = jsonObjectUserData.getBoolean("isparentcustomer")
                            livedataLoginRepository.postValue(
                                LoginRepositoryModel(
                                    is_success,
                                    user_first_name,
                                    user_last_name,
                                    user_email,
                                    user_number,
                                    user_reg_id,
                                    isparentcustomer,
                                    token
                                )
                            )
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    try {
                        assert(response.errorBody() != null)
                        val res = response.errorBody()!!.string()
                        res.let {
                            System.err.println("res-->$res")
                            val jsonObject = JSONObject(res)
                            val is_success = jsonObject.getBoolean("issuccess")
                            val message = jsonObject.getString("message")
                            livedataLoginRepository.postValue(
                                LoginRepositoryModel(
                                    is_success = is_success,
                                    message = message
                                )
                            )
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                livedataLoginRepository.postValue(
                    LoginRepositoryModel(
                        is_success = false,
                        message = t.message
                    )
                )
            }
        })

        return livedataLoginRepository
    }

}