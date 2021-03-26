package com.avans.assessment.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContactListItem(name: String) {
    Surface(shape = RoundedCornerShape(4.dp),
        elevation = 8.dp,
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {

        Box(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
            Text(name)
        }
    }
}