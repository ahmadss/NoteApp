package id.co.example.noteapp.note_feature.presentation.notes

import id.co.example.noteapp.note_feature.domain.model.Note
import id.co.example.noteapp.note_feature.domain.utility.NoteOrder

sealed class NotesEvent{
    data class order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}