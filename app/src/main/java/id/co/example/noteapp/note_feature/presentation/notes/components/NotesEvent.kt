package id.co.example.noteapp.note_feature.presentation.notes.components

import id.co.example.noteapp.note_feature.domain.model.Note
import id.co.example.noteapp.note_feature.domain.utility.NoteOrder
import id.co.example.noteapp.note_feature.domain.utility.OrderType

sealed class NotesEvent{
    data class order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}