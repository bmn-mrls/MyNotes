package com.bmnmrls.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    val id: Long? = null,
    var content: String,
    var date: Date,
    var isFavorite: Boolean,
    var firstColor: String,
    var secondColor: String
) : Parcelable