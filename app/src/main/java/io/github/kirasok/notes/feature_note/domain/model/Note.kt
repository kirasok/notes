package io.github.kirasok.notes.feature_note.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.kirasok.notes.ui.theme.BabyBlue
import io.github.kirasok.notes.ui.theme.LightGreen
import io.github.kirasok.notes.ui.theme.RedOrange
import io.github.kirasok.notes.ui.theme.RedPink
import io.github.kirasok.notes.ui.theme.Violet

@Entity
data class Note(
  @ColumnInfo val title: String,
  @ColumnInfo val content: String,
  @ColumnInfo val timestamp: Long,
  @ColumnInfo val color: Int,
  @ColumnInfo @PrimaryKey val id: Int? = null,
) {
  companion object {
    val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
  }
}

class InvalidNoteException(message: String) : Exception(message)