package com.avans.assessment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.avans.assessment.models.Beer
import com.avans.assessment.ui.theme.Typography

@Composable
fun BeerListItem(item: Beer, onClick: (Beer) -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(55.dp)
            .clickable { onClick(item) })
    {
        Column(Modifier.padding(8.dp)) {
            Text(text = item.name, textAlign = TextAlign.Left, style = Typography.body1)
            Text(text = item.firstBrewed, textAlign = TextAlign.Left, style = Typography.caption)
        }
    }
}