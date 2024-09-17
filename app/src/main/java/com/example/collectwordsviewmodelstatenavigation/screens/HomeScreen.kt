package com.example.collectwordsviewmodelstatenavigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    words: List<String>,
    onAddWord: (String) -> Unit,
    onRemoveWord: (String) -> Unit,
    onClearList: () -> Unit,
    onShowList: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Collect words"
                    )
                }
            )
        }
    ) { innerPadding ->
        CollectWords(
            modifier = modifier.padding(innerPadding),
            words = words,
            onAddWord = onAddWord,
            onRemoveWord = onRemoveWord,
            onClearList = onClearList,
            onShowList = onShowList
        )
    }
}

@Composable
fun CollectWords(
    modifier: Modifier = Modifier,
    words: List<String>,
    onAddWord: (String) -> Unit,
    onRemoveWord: (String) -> Unit,
    onClearList: () -> Unit,
    onShowList: () -> Unit = {}
) {
    // Add to gradle file  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    // https://tigeroakes.com/posts/mutablestateof-list-vs-mutablestatelistof/
    // val words = viewModel.words
    var word by remember { mutableStateOf("") }
    var showList by remember { mutableStateOf(true) }

    Column(modifier = modifier.padding(10.dp)) {
        OutlinedTextField(
            value = word,
            onValueChange = { word = it },
            // https://medium.com/@GkhKaya00/exploring-keyboard-types-in-kotlin-jetpack-compose-ca1f617e1109
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter a word") }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                onAddWord(word)
                // viewModel.onAddWord(word)
            }) {
                Text("Add")
            }
            Button(onClick = {
                onClearList()
                word = ""
            }) {
                Text("Clear")
            }
            Button(onClick = { onShowList() }) {
                Text("Show")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show list")
            Spacer(modifier = Modifier.padding(5.dp))
            Switch(checked = showList, onCheckedChange = { showList = it })
        }
        if (showList) {
            if (words.isEmpty()) {
                Text("No words")
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(words) { word: String ->
                        Text(word, modifier = Modifier.clickable { onRemoveWord(word) })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectWordsPreview() {
    Home(
        words = listOf("Hello", "World"),
        onAddWord = {},
        onRemoveWord = {},
        onClearList = {})
}
