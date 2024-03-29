package id.co.example.noteapp.note_feature.presentation.notes.components

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.co.example.noteapp.note_feature.presentation.notes.NoteViewModel
import id.co.example.noteapp.note_feature.presentation.notes.NotesEvent
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel : NoteViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Log.d("stateValue",state.notes.toString())
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick =
                {
//                    navController.navigate(Screens.AddEditNoteScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            )
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }, scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Note",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.ToggleOrderSection)
                    },
                )
                {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort the Notes"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    noteOrder = state.noteOrder, onOrderChange = {
//                        viewModel.onEvent(NotesEvent.Order(it))
                    })
            }
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notes) { note ->
//                    Log.d("NoteID",note.id.toString())
                    SingleItemNote(note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            },
                        onDeleteClik = {
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            coroutineScope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )
                                if(result==SnackbarResult.ActionPerformed){
                                    viewModel.onEvent((NotesEvent.RestoreNote))
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}