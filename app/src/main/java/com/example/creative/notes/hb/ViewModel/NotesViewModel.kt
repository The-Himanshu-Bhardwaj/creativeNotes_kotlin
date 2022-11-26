package com.example.creative.notes.hb.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.creative.notes.hb.Database.NoteDatabase
import com.example.creative.notes.hb.Models.NotesModel
import com.example.creative.notes.hb.Repository.NoteRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private var repository : NoteRepository

    init {
        val dao = NoteDatabase.getDatabaseInstance(application).getDao()
        repository = NoteRepository(dao)
    }

    fun getAllNotes() : LiveData<List<NotesModel>> {
         return repository.getAllNotes()
    }

    fun getHighNotes() : LiveData<List<NotesModel>> = repository.getHighNotes()
    fun getMediumNotes() : LiveData<List<NotesModel>> = repository.getMediumNotes()
    fun getLowNotes() : LiveData<List<NotesModel>> = repository.getLowNotes()

    fun addNote(note: NotesModel) {
        repository.insertNote(note)
    }

    fun deleteNote(id : Int) {
        repository.deleteNote(id)
    }

    fun updateNote(note: NotesModel) {
        repository.updateNote(note)
    }

}