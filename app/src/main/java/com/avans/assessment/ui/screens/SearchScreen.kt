package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.avans.assessment.models.Beer
import com.avans.assessment.ui.Routes
import com.avans.assessment.ui.components.BeerListItem
import com.avans.assessment.ui.components.general.Centered
import com.avans.assessment.ui.components.general.CenteredProgressIndicator
import com.avans.assessment.viewmodels.SearchViewModel

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
        val error = searchViewModel.error

        if (error != null) {
            ErrorScreen(error)
            return@Scaffold
        }

        Column(Modifier.fillMaxWidth()) {
            SearchList(context, searchViewModel) {
                navController.navigate(Routes.compoundUrl(Routes.DETAIL, "id", it.id.toString()))
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
fun SearchList(context: Context, searchViewModel: SearchViewModel, itemClick: (Beer) -> Unit) {
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
                BeerListItem(context, item = it, onClick = itemClick)
            }
        }
    }
}
