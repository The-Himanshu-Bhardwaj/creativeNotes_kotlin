package com.example.creative.notes.hb.Database

import android.content.Context
import android.os.Build.VERSION_CODES.N
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.creative.notes.hb.DAO.NoteDAO
import com.example.creative.notes.hb.Models.NotesModel

@Database(entities = [NotesModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getDao(): NoteDAO


    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabaseInstance(context: Context): NoteDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java,
                        "notes")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}