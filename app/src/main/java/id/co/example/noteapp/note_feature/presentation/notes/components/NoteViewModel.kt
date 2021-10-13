package id.co.example.noteapp.note_feature.presentation.notes.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.example.noteapp.note_feature.domain.model.Note
import id.co.example.noteapp.note_feature.domain.use_case.NotesUseCase
import id.co.example.noteapp.note_feature.domain.utility.NoteOrder
import id.co.example.noteapp.note_feature.domain.utility.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteUseCase: NotesUseCase
) : ViewModel() {

    private val mutableState = mutableStateOf(NotesState())
    private val state: State<NotesState> = mutableState
    private var recentDeleteNote: Note? = null
    private var getAllNotesJob:Job?=null

    init {
        getAllNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.order -> {
                if (state.value.noteOrder::class == event.noteOrder.orderType::class && state.value.noteOrder.orderType == event.noteOrder.orderType) {
                    return
                }

                getAllNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNote(event.note)
                    recentDeleteNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCase.addNote(recentDeleteNote ?: return@launch)
                    recentDeleteNote = null
                }

            }

            is NotesEvent.ToggleOrderSection -> {
                mutableState.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getAllNotes(noteOrder: NoteOrder) {
        getAllNotesJob?.cancel()
        getAllNotesJob = noteUseCase.getNotes(noteOrder)
            .onEach { notes->
                mutableState.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}