package id.co.example.noteapp.note_feature.domain.use_case

import id.co.example.noteapp.note_feature.domain.model.Note
import id.co.example.noteapp.note_feature.domain.repository.NoteRepository
import id.co.example.noteapp.note_feature.domain.utility.NoteOrder
import id.co.example.noteapp.note_feature.domain.utility.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getAllNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy {
                            it.title.lowercase()
                        }
                        is NoteOrder.Date -> notes.sortedBy {
                            it.timestamp
                        }
                        is NoteOrder.Color -> notes.sortedBy {
                            it.color
                        }
                    }
                }

                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedByDescending {
                            it.title.lowercase()
                        }
                        is NoteOrder.Date -> notes.sortedByDescending {
                            it.timestamp
                        }
                        is NoteOrder.Color -> notes.sortedByDescending {
                            it.color
                        }
                    }
                }
            }
        }
    }
}