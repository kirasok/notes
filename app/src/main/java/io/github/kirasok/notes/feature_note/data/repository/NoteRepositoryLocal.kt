package io.github.kirasok.notes.feature_note.data.repository

import io.github.kirasok.notes.feature_note.data.source.NoteDao
import io.github.kirasok.notes.feature_note.domain.model.Note
import io.github.kirasok.notes.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryLocal(private val dao: NoteDao) : NoteRepository {
  override fun getNotes(): Flow<List<Note>> = dao.getNotes()

  override suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)

  override suspend fun insertNote(note: Note) = dao.insertNote(note)

  override suspend fun deleteNode(note: Note) = dao.deleteNote(note)
}