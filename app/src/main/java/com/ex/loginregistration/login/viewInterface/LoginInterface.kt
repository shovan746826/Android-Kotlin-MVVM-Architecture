package com.ex.loginregistration.login.viewInterface

import androidx.lifecycle.MutableLiveData
import com.ex.loginregistration.login.model.LoginRepositoryModel

interface LoginInterface {
    fun onStarted()
    fun onSuccess(liveData: MutableLiveData<LoginRepositoryModel>)
    fun onFailure(message: String)
}