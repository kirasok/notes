package io.github.kirasok.notes.feature_note.presentation.editor

import androidx.compose.ui.focus.FocusState

sealed class EditorEvent {
  data class EnteredTitle(val value: String) : EditorEvent()
  data class ChangeTitleFocus(val focusState: FocusState) : EditorEvent()
  data class EnteredContent(val value: String) : EditorEvent()
  data class ChangeContentFocus(val focusState: FocusState) : EditorEvent()
  data class ChangeColor(val color: Int) : EditorEvent()
  object SaveNote : EditorEvent()
}