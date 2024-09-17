package com.example.collectwordsviewmodelstatenavigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Show(words: List<String>, onNavigateBack: () -> Unit, onDeleteWord: (String) -> Unit) {
    Scaffold { innerPadding ->
        WordsList(
            words = words,
            onNavigateBack = onNavigateBack,
            modifier = Modifier.padding(innerPadding),
            onDeleteWord = onDeleteWord
        )
    }
}

@Composable
fun WordsList(
    words: List<String>,
    modifier: Modifier,
    onNavigateBack: () -> Unit,
    onDeleteWord: (String) -> Unit
) {
    Column(modifier = modifier) {
        Button(
            onClick = onNavigateBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text("Go back")
        }
        if (words.isEmpty()) {
            Text("No words to show")
        } else {
            LazyColumn {
                items(words) { word ->
                    Card(onClick = { onDeleteWord(word) },
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                    ) {
                        Text(modifier = Modifier.padding(8.dp), text = word)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowPreview() {
    Show(words = listOf("Hello", "World"), onNavigateBack = {}, onDeleteWord = {})
}