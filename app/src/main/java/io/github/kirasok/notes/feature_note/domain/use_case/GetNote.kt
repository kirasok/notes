package io.github.kirasok.notes.feature_note.domain.use_case

import io.github.kirasok.notes.feature_note.domain.model.Note
import io.github.kirasok.notes.feature_note.domain.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {
  suspend operator fun invoke(id: Int): Note? = repository.getNoteById(id)
}