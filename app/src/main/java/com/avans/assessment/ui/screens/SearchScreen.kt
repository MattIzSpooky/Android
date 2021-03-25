package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.avans.assessment.viewmodels.SearchViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.avans.assessment.models.Beer
import com.avans.assessment.ui.components.BeerListItem
import com.avans.assessment.ui.components.Centered
import com.avans.assessment.ui.components.CenteredProgressIndicator

@Composable
fun SearchScreen(context: Context, navController: NavHostController, searchText: String = "") {
    val searchViewModel = SearchViewModel(context, searchText)

    searchViewModel.search(searchText)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { SearchInput(searchViewModel) }
            )
        }
    ) {
        Column(Modifier.fillMaxWidth()) {
            SearchList(searchViewModel) {
                navController.navigate("detail/${it.id}")
            }
        }
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
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
        )
    )
}

@Composable
fun SearchList(searchViewModel: SearchViewModel, itemClick: (Beer) -> Unit) {
    if (searchViewModel.isFetching) {
        CenteredProgressIndicator()
        return
    }

    if (searchViewModel.searchResults.isEmpty()) {
        Centered {
            Text("No search results")
        }
        return
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(searchViewModel.searchResults) {
                BeerListItem(item = it, itemClick)
            }
        }
    }
}
