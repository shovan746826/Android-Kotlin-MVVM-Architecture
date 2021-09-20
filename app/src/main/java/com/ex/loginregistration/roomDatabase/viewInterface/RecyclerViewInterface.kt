package com.ex.loginregistration.roomDatabase.viewInterface

import android.view.View
import com.ex.loginregistration.roomDatabase.entityTables.NoteTable

interface RecyclerViewInterface {
    fun onDeleteItemClick(view: View, noteTable: NoteTable)
}