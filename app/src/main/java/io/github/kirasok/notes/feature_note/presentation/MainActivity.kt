package io.github.kirasok.notes.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.github.kirasok.notes.feature_note.presentation.editor.EditorScreen
import io.github.kirasok.notes.feature_note.presentation.notes.NotesScreen
import io.github.kirasok.notes.feature_note.presentation.util.Screen
import io.github.kirasok.notes.ui.theme.NotesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      NotesTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
          val navController = rememberNavController()
          NavHost(navController = navController, startDestination = Screen.NotesScreen.route) {
            composable(route = Screen.NotesScreen.route) {
              NotesScreen(navController = navController)
            }
            composable(route = Screen.EditorScreen.route + "?noteId={noteId}&noteColor={noteColor}",
              arguments = listOf(
                navArgument(
                  name = "noteId",
                ) {
                  type = NavType.IntType
                  defaultValue = -1
                },
                navArgument(
                  name = "noteColor",
                ) {
                  type = NavType.IntType
                  defaultValue = -1
                }
              )
            ) {
              EditorScreen(
                navController = navController,
                noteColor = it.arguments?.getInt("noteColor") ?: -1
              )
            }
          }
        }
      }
    }
  }
}