package io.github.kirasok.notes.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kirasok.notes.feature_note.data.repository.NoteRepositoryLocal
import io.github.kirasok.notes.feature_note.data.source.NoteDatabase
import io.github.kirasok.notes.feature_note.domain.repository.NoteRepository
import io.github.kirasok.notes.feature_note.domain.use_case.AddNote
import io.github.kirasok.notes.feature_note.domain.use_case.DeleteNote
import io.github.kirasok.notes.feature_note.domain.use_case.GetNote
import io.github.kirasok.notes.feature_note.domain.use_case.GetNotes
import io.github.kirasok.notes.feature_note.domain.use_case.NoteUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun providesNoteDatabase(app: Application): NoteDatabase = Room.databaseBuilder(
    app,
    NoteDatabase::class.java,
    NoteDatabase.DATABASE_NAME
  ).build()

  @Provides
  @Singleton
  fun providesNoteRepository(db: NoteDatabase): NoteRepository = NoteRepositoryLocal(db.noteDao)

  @Provides
  @Singleton
  fun providesNoteUseCases(repository: NoteRepository): NoteUseCases = NoteUseCases(
    getNotes = GetNotes(repository),
    deleteNote = DeleteNote(repository),
    addNote = AddNote(repository),
    getNote = GetNote(repository)
  )
}