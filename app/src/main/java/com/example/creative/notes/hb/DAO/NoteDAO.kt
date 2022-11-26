package com.example.creative.notes.hb.DAO

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.creative.notes.hb.Models.NotesModel

@Dao
interface NoteDAO {

    @Query("SELECT * FROM notes")
    fun getNotes() : LiveData<List<NotesModel>>

    @Query("SELECT * FROM notes WHERE priority=3")
    fun getHighNotes() : LiveData<List<NotesModel>>

    @Query("SELECT * FROM notes WHERE priority=2")
    fun getMediumNotes() : LiveData<List<NotesModel>>

    @Query("SELECT * FROM notes WHERE priority=1")
    fun getLowNotes() : LiveData<List<NotesModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note : NotesModel)

    @Update
    fun updateNote(note: NotesModel)

    @Query("DELETE FROM notes WHERE id =:id")
    fun deleteNote(id : Int)
}