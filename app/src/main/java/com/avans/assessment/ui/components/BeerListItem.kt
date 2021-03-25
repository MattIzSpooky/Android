package com.avans.assessment.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.avans.assessment.models.Beer
import com.avans.assessment.ui.theme.Typography

@Composable
fun BeerListItem(item: Beer, onClick: (Beer) -> Unit, onLongPress: ((Beer) -> Unit)? = null) {
    Card(shape = RoundedCornerShape(4.dp),
        backgroundColor = Color.DarkGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(50.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        if (onLongPress != null) {
                            onLongPress(item)
                        }
                    },
                    onTap = { onClick(item) }
                )
            }) {

        Column(Modifier.padding(8.dp)) {
            Text(text = item.name, textAlign = TextAlign.Left, style = Typography.body1)
            Text(text = item.firstBrewed, textAlign = TextAlign.Left, style = Typography.caption)
        }
    }
}