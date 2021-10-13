package id.co.example.noteapp.note_feature.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.PrimaryKey
import id.co.example.noteapp.ui.theme.Purple200
import id.co.example.noteapp.ui.theme.Purple700
import id.co.example.noteapp.ui.theme.Teal200

data class Note(
    val title : String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val Id : Int? = null
) {
    companion object{
        val noteColorsList = listOf(Purple200, Color.Red, Color.Green, Color.Blue, Color.LightGray, Teal200)
    }
}

class InvalidAddNoteException(message:String):Exception(message)
