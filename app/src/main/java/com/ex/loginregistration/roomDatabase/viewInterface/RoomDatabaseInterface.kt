package com.ex.loginregistration.roomDatabase.viewInterface

interface RoomDatabaseInterface {
    fun onStarted()
    fun onSuccess(message: String)
    fun onFailure(message: String)
}