package io.github.kirasok.notes.feature_note.domain.use_case

data class NoteUseCases(
  val getNotes: GetNotes,
  val deleteNote: DeleteNote,
  val addNote: AddNote,
  val getNote: GetNote,
)
