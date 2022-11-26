package com.example.creative.notes.hb.Models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class NotesModel (

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var title: String,
    var subtitle: String,
    var note: String,
    var date: String,
    var priority: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeString(note)
        parcel.writeString(date)
        parcel.writeString(priority)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NotesModel> {
        override fun createFromParcel(parcel: Parcel): NotesModel {
            return NotesModel(parcel)
        }

        override fun newArray(size: Int): Array<NotesModel?> {
            return arrayOfNulls(size)
        }
    }
}