package com.ex.loginregistration.login.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ex.loginregistration.login.repository.LoginRepository
import com.ex.loginregistration.login.viewInterface.LoginInterface

class LoginViewModel : ViewModel() {

    var userId: MutableLiveData<String?> = MutableLiveData()
    var password: MutableLiveData<String?> = MutableLiveData()
    var loginInterface: LoginInterface? = null
    private var loginRepository: LoginRepository = LoginRepository()

    fun onLoginButtonClick(view: View) {
        view.let {
            loginInterface?.onStarted()

            if (userId.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
                loginInterface?.onFailure("User id & Password not match")
                return
            }
            //success
            val liveData = loginRepository.getLoginResponse(userId.value!!, password.value!!)
            loginInterface?.onSuccess(liveData)
        }
    }
}