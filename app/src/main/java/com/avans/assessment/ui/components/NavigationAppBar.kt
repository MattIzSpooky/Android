package com.avans.assessment.ui.components

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun NavigationAppBar(name: String, scaffoldState: ScaffoldState){
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = name) },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                        else scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(Icons.Default.Menu, "Menu button")
                CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
            }
        }
    )
}