package com.ex.loginregistration.roomDatabase.repository

import androidx.lifecycle.LiveData
import com.ex.loginregistration.roomDatabase.NoteDao
import com.ex.loginregistration.roomDatabase.entityTables.NoteTable

class NoteRepository(private val noteDao: NoteDao) {

    val getAllNoteData: LiveData<List<NoteTable>> = noteDao.getAllData()

    suspend fun insert(note : NoteTable): Long{
        return noteDao.insert(note)
    }

    suspend fun delete(note: NoteTable){
        noteDao.delete(note)
    }
}