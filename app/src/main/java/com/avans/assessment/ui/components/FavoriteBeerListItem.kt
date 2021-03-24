package com.avans.assessment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.avans.assessment.db.entities.FavoriteBeer

@Composable
fun FavoriteBeerListItem(item: FavoriteBeer, onClick: (item: FavoriteBeer) -> Unit) {
    Card( shape = RoundedCornerShape(4.dp),
        backgroundColor = Color.DarkGray,
        modifier = Modifier.fillMaxWidth().padding(8.dp).height(50.dp).clickable { onClick(item) }) {

        Text(text = "" + item.id + " - " + item.name)
    }
}