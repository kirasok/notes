package io.github.kirasok.notes.feature_note.presentation.util

sealed class Screen(val route: String) {
  object NotesScreen : Screen("notes_screen")
  object EditorScreen : Screen("editor_screen")
}
