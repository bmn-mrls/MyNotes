package com.bmnmrls.data.local.mappers

import com.bmnmrls.data.local.models.NoteEntity
import com.bmnmrls.domain.mappers.Transform
import com.bmnmrls.domain.models.Note
import javax.inject.Inject

class NoteEntityMapper @Inject constructor() : Transform<NoteEntity, Note>() {

    override fun transform(value: NoteEntity) = Note(
        value.id,
        value.content,
        value.date,
        value.isFavorite,
        value.firstColor,
        value.secondColor
    )

}