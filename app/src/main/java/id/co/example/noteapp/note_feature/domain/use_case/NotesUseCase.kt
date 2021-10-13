package id.co.example.noteapp.note_feature.domain.use_case

class NotesUseCase(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
    val getNoteById: GetNoteByIdUseCase
) {
}