package com.ex.loginregistration.globals

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast


fun Context.toast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun Context.alertDialog(title: String, message: String){
    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    alertDialog.setTitle(title)
    alertDialog.setMessage(message)
    alertDialog.setPositiveButton("Ok") { dialog, _ ->
        dialog.dismiss()
        dialog.cancel()
    }
    alertDialog.create()
    alertDialog.show()
}
