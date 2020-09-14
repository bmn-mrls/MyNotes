package com.bmnmrls.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(
    val id: Long? = null,
    var content: String,
    var date: String,
    var isFavorite: Boolean,
    val firstColor: String,
    val secondColor: String
) : Parcelable