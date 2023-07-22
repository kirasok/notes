package io.github.kirasok.notes.feature_note.presentation.editor

data class NoteTextFieldState(
  val text: String = "",
  val hint: String = "",
  val isHintVisible: Boolean = true,
)
