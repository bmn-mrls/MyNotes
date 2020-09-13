package com.bmnmrls.data.local.mappers

import com.bmnmrls.data.local.models.NoteEntity
import com.bmnmrls.domain.mappers.Transform
import com.bmnmrls.domain.models.Note

class NoteEntityMapper : Transform<NoteEntity, Note>() {

    override fun transform(value: NoteEntity) = Note(
        value.id,
        value.content,
        value.date,
        value.isFavorite
    )

}