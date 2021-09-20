package com.ex.loginregistration.roomDatabase.entityTables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class NoteTable(@ColumnInfo(name = "name") val name: String, val id: Int) {
    @PrimaryKey(autoGenerate = false) var pk = name + id
}