package com.bmnmrls.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean

)