package id.co.example.noteapp.note_feature.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.co.example.noteapp.note_feature.domain.utility.NoteOrder
import id.co.example.noteapp.note_feature.domain.utility.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit,

    ) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Title", checked = noteOrder is NoteOrder.Title, oncheck = {
                onOrderChange(NoteOrder.Title(noteOrder.orderType))
            })
            Spacer(modifier = Modifier.width(10.dp))
            DefaultRadioButton(text = "Date", checked = noteOrder is NoteOrder.Date, oncheck = {
                onOrderChange(NoteOrder.Date(noteOrder.orderType))
            })
            Spacer(modifier = Modifier.width(10.dp))
            DefaultRadioButton(text = "Color", checked = noteOrder is NoteOrder.Color, oncheck = {
                onOrderChange(NoteOrder.Color(noteOrder.orderType))
            })
            Spacer(modifier = Modifier.width(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                DefaultRadioButton(
                    text = "Ascending",
                    checked = noteOrder.orderType is OrderType.Ascending,
                    oncheck = {
                        onOrderChange(noteOrder.copy(OrderType.Ascending))
                    })
                DefaultRadioButton(
                    text = "Descending",
                    checked = noteOrder.orderType is OrderType.Descending,
                    oncheck = {
                        onOrderChange(noteOrder.copy(OrderType.Descending))
                    })

            }
        }
    }
}