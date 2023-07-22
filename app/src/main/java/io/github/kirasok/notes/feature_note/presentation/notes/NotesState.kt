package io.github.kirasok.notes.feature_note.presentation.notes

import io.github.kirasok.notes.feature_note.domain.model.Note
import io.github.kirasok.notes.feature_note.domain.util.NoteOrder

data class NotesState(
  val notes: List<Note> = emptyList(),
  val noteOrder: NoteOrder = NoteOrder.default,
  val isOrderSectionVisible: Boolean = false,
)
