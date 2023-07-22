package io.github.kirasok.notes.feature_note.domain.use_case

import io.github.kirasok.notes.feature_note.domain.model.Note
import io.github.kirasok.notes.feature_note.domain.repository.NoteRepository
import io.github.kirasok.notes.feature_note.domain.util.NoteOrder
import io.github.kirasok.notes.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(private val noteRepository: NoteRepository) {
  operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> =
    noteRepository.getNotes()
      .map { notes ->
        when (noteOrder.orderType) {
          is OrderType.Ascending -> {
            when (noteOrder) {
              is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
              is NoteOrder.Date -> notes.sortedBy { it.timestamp }
              is NoteOrder.Color -> notes.sortedBy { it.color }
            }
          }

          is OrderType.Descending -> {
            when (noteOrder) {
              is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
              is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
              is NoteOrder.Color -> notes.sortedByDescending { it.color }
            }
          }
        }
      }
}