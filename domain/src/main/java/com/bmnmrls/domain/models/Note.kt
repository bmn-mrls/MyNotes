package com.bmnmrls.domain.models

data class Note(
    val id: Long? = null,
    val content: String,
    val date: String,
    val isFavorite: Boolean,
    val firstColor: String,
    val secondColor: String
)