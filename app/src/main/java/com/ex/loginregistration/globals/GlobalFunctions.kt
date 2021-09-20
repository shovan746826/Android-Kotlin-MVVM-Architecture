package com.ex.loginregistration.globals

import android.app.Activity
import android.app.ProgressDialog

class GlobalFunctions(activity: Activity) {
    private val dialog = ProgressDialog(activity)

    fun showLoadingDialog(){
        dialog.setTitle("Loading...")
        dialog.create()
        dialog.show()
    }

    fun hideLoadingDialog(){
        dialog.cancel()
        dialog.hide()
    }
}