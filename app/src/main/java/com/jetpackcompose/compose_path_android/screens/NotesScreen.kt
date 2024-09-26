package com.jetpackcompose.compose_path_android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

data class Note(val title: String, val content: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesApp() {
    var notes by remember { mutableStateOf(listOf<Note>(Note("Note 1", "Content 1"), Note("Note 2", "Content 2"))) }
    var showDialog by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        isLoading = true
        delay(5000)
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notes App") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (notes.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No notes yet. Add your first note!")
                }
            } else {
                LazyColumn {
                    items(notes) { note ->
                        NoteItem(note , isLoading)
                    }
                }
            }
        }
    }

    if (showDialog) {
        AddNoteDialog(
            onDismiss = { showDialog = false },
            onNoteAdded = { title, content ->
                notes = notes + Note(title, content)
                showDialog = false
            }
        )
    }
}


@Composable
fun NoteItem(note: Note, isLoading: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        ShimmerSkeleton(isLoading = isLoading,content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = note.title, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = note.content, style = MaterialTheme.typography.titleLarge)
            }
        })

    }
}

@Composable
fun AddNoteDialog(onDismiss: () -> Unit, onNoteAdded: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Note") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onNoteAdded(title, content) }) {
                Text("Add Note")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}