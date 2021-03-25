package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.avans.assessment.viewmodels.SearchViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun SearchScreen(context: Context, searchText: String = "") {
    val searchViewModel = SearchViewModel(context, searchText)

    searchViewModel.search(searchText)

    Column(Modifier.fillMaxWidth()) {
        SearchInput(searchViewModel)
        SearchList(searchViewModel)
    }
}

@Composable
fun SearchInput(searchViewModel: SearchViewModel) {
    var text by remember { mutableStateOf(searchViewModel.searchText) }

    TextField(
        value = text,
        onValueChange = {
            text = it

            searchViewModel.search(text)
        },
        label = { Text("Enter a beer name") }
    )
}

@Composable
fun SearchList(searchViewModel: SearchViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(searchViewModel.searchResults) {
                Text(it.name)
            }
        }
    }
}
