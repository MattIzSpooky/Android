package com.avans.assessment.ui.components

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomBar(
    homeListener: () -> Unit,
    favoriteListener: () -> Unit,
) {
    BottomAppBar {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = homeListener
        ) {
            Icon(Icons.Filled.Home, "home")
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = favoriteListener
        ) {
            Icon(Icons.Filled.Favorite, "favorite")
        }
    }
}