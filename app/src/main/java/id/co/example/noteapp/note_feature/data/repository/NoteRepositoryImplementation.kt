package id.co.example.noteapp.note_feature.data.repository

import id.co.example.noteapp.note_feature.data.data_source.NoteDao
import id.co.example.noteapp.note_feature.domain.model.Note
import id.co.example.noteapp.note_feature.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImplementation (
    private val dao : NoteDao
) : NoteRepository{

    override fun getAllNotes(): Flow<List<Note>> {
       return dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.DeleteNote(note)
    }

}