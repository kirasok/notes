package io.github.kirasok.notes.feature_note.presentation.editor

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.kirasok.notes.feature_note.domain.model.Note
import io.github.kirasok.notes.feature_note.presentation.editor.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditorScreen(
  navController: NavController,
  noteColor: Int,
  viewModel: EditorViewModel = hiltViewModel(),
) {
  val titleState = viewModel.noteTitle.value
  val contentState = viewModel.noteContent.value

  val snackbarHostState = remember { SnackbarHostState() }

  val noteBackgroundAnimatable =
    remember { Animatable(Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)) }

  val scope = rememberCoroutineScope()

  LaunchedEffect(key1 = true) {
    viewModel.eventFlow.collectLatest { event ->
      when (event) {
        is EditorViewModel.UiEvent.ShowSnackbar -> snackbarHostState.showSnackbar(message = event.message)
        is EditorViewModel.UiEvent.SaveNote -> navController.navigateUp()
      }
    }
  }

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        onClick = { viewModel.onEvent(EditorEvent.SaveNote) },
        contentColor = MaterialTheme.colorScheme.primary
      ) {
        Icon(
          imageVector = Icons.Default.Save, contentDescription = "Save note"
        )
      }
    },
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(noteBackgroundAnimatable.value)
        .padding(padding)
        .padding(16.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Note.noteColors.forEach { color ->
          val colorInt = color.toArgb()
          Box(
            modifier = Modifier
              .size(50.dp)
              .shadow(15.dp, CircleShape)
              .clip(CircleShape)
              .background(color)
              .border(
                width = 3.dp,
                color = if (viewModel.noteColor.value == colorInt) Color.Black else Color.Transparent,
                shape = CircleShape
              )
              .clickable {
                scope.launch {
                  noteBackgroundAnimatable.animateTo(
                    targetValue = color,
                    animationSpec = tween(
                      durationMillis = 500
                    )
                  )
                }
                viewModel.onEvent(EditorEvent.ChangeColor(colorInt))
              }
          )
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
      TransparentHintTextField(
        text = titleState.text,
        hint = titleState.hint,
        onValueChange = {
          viewModel.onEvent(EditorEvent.EnteredTitle(it))
        },
        onFocusChange = { viewModel.onEvent(EditorEvent.ChangeTitleFocus(it)) },
        isHintVisible = titleState.isHintVisible,
        singleLine = true,
        textStyle = MaterialTheme.typography.titleMedium,
      )
      Spacer(modifier = Modifier.height(16.dp))
      TransparentHintTextField(
        text = contentState.text,
        hint = contentState.hint,
        onValueChange = {
          viewModel.onEvent(EditorEvent.EnteredContent(it))
        },
        onFocusChange = { viewModel.onEvent(EditorEvent.ChangeContentFocus(it)) },
        isHintVisible = contentState.isHintVisible,
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxSize(),
      )
    }
  }
}