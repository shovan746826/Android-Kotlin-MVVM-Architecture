package com.ex.loginregistration.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ex.loginregistration.R
import com.ex.loginregistration.databinding.ActivityLoginBinding
import com.ex.loginregistration.globals.GlobalFunctions
import com.ex.loginregistration.globals.alertDialog
import com.ex.loginregistration.globals.toast
import com.ex.loginregistration.home.view.HomeActivity
import com.ex.loginregistration.login.model.LoginRepositoryModel
import com.ex.loginregistration.login.viewInterface.LoginInterface
import com.ex.loginregistration.login.viewModel.LoginViewModel
import com.ex.loginregistration.roomDatabase.view.RoomDatabaseActivity

class LoginActivity : AppCompatActivity(), LoginInterface {

    private lateinit var globalFunctions: GlobalFunctions
    private lateinit var observer: Observer<LoginRepositoryModel>
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.loginInterface = this


        globalFunctions = GlobalFunctions(this)

//        startActivity(Intent(this, RoomDatabaseActivity::class.java))
//        finish()

        setObserver()
    }

    private fun setObserver() {
        observer = Observer { loginResponseModel ->
            loginResponseModel?.let {
                if (it.is_success) {
                    it.token?.let { _it ->
                        toast(_it)
                        startActivity(Intent(this, RoomDatabaseActivity::class.java))
                        finish()
                    }
                } else {
                    it.message?.let { _it ->
                        alertDialog("Error!", _it)
                        viewModel.password.value = null
                    }
                }
                globalFunctions.hideLoadingDialog()
            }
        }
    }

    override fun onStarted() {
        globalFunctions.showLoadingDialog()
    }

    override fun onSuccess(liveData: MutableLiveData<LoginRepositoryModel>) {
        liveData.observe(this, observer)
    }

    override fun onFailure(message: String) {
        globalFunctions.hideLoadingDialog()
        alertDialog("Error!", message)
    }
}