package com.ex.loginregistration.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ex.loginregistration.roomDatabase.entityTables.NoteTable

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteTable: NoteTable): Long

    @Delete
    suspend fun delete(noteTable: NoteTable)

    @Query("Select * from note_table order by id DESC")
    fun getAllData(): LiveData<List<NoteTable>>
}