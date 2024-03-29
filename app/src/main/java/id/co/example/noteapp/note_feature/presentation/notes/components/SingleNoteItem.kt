package id.co.example.noteapp.note_feature.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import id.co.example.noteapp.note_feature.domain.model.Note

@Composable
fun SingleItemNote(
    modifier: Modifier = Modifier,
    note: Note,
    cornerRadius: Dp = 10.dp,
    onDeleteClik: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 30.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }
        IconButton(onClick = onDeleteClik, modifier = Modifier.align(Alignment.BottomEnd)) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Note",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}