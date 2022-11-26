package com.example.creative.notes.hb.Repository

import androidx.lifecycle.LiveData
import com.example.creative.notes.hb.DAO.NoteDAO
import com.example.creative.notes.hb.Models.NotesModel
import java.io.NotSerializableException

class NoteRepository(private val noteDAO: NoteDAO) {


    fun getAllNotes() : LiveData<List<NotesModel>> {
        return noteDAO.getNotes()
    }

    fun getHighNotes() : LiveData<List<NotesModel>> = noteDAO.getHighNotes()
    fun getMediumNotes() : LiveData<List<NotesModel>> = noteDAO.getMediumNotes()
    fun getLowNotes() : LiveData<List<NotesModel>> = noteDAO.getLowNotes()

    fun insertNote(note : NotesModel) {
        noteDAO.insertNote(note)
    }

    fun deleteNote(id : Int) {
        noteDAO.deleteNote(id)
    }

    fun updateNote(note: NotesModel) {
        noteDAO.updateNote(note)
    }
}