package id.co.example.noteapp.note_feature.data.data_source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import id.co.example.noteapp.note_feature.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteDao{

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id= :id")
    suspend fun getNotById(id:Int):Note?

    @Insert(onConflict = REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun DeleteNote(note:Note)
}