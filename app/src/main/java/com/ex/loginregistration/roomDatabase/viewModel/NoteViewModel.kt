package com.ex.loginregistration.roomDatabase.viewModel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ex.loginregistration.roomDatabase.NoteDatabase
import com.ex.loginregistration.roomDatabase.entityTables.NoteTable
import com.ex.loginregistration.roomDatabase.repository.NoteRepository
import com.ex.loginregistration.roomDatabase.viewInterface.RoomDatabaseInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    var editTextLiveData: MutableLiveData<String?> = MutableLiveData()
    var roomDatabaseInterface: RoomDatabaseInterface? = null
    var liveDataGetAllNote: LiveData<List<NoteTable>>
    private var repository : NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(noteDao)
        liveDataGetAllNote = repository.getAllNoteData
    }

    fun deleteNote(noteTable: NoteTable) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(noteTable)
    }

    private fun insertNote(noteTable: NoteTable) = viewModelScope.launch(Dispatchers.IO) {
        val id = repository.insert(noteTable)
        if (id == -1L) {
            roomDatabaseInterface?.onFailure("Insert Failed")
        }else{
            roomDatabaseInterface?.onSuccess("Insert Success")
        }
    }

    fun onAddButtonClick(view: View){
        roomDatabaseInterface?.onStarted()
        view.let {
            if (editTextLiveData.value?.trim().isNullOrEmpty()){
                roomDatabaseInterface?.onFailure("Enter value")
                return
            }
            insertNote(NoteTable(editTextLiveData.value!!,0))
        }
    }

}