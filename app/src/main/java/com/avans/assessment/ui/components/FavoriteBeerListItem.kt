package com.avans.assessment.ui.components

import android.content.Context
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.avans.assessment.db.entities.FavoriteBeer
import com.avans.assessment.ui.theme.Typography

@Composable
fun FavoriteBeerListItem(
    context: Context,
    item: FavoriteBeer,
    onClick: (item: FavoriteBeer) -> Unit,
    onDoubleTap: (item: FavoriteBeer) -> Unit,
    onLongPress: (item: FavoriteBeer) -> Unit
) {
    Surface(shape = RoundedCornerShape(4.dp),
        elevation = 8.dp,
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .sizeIn(minHeight = 20.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongPress(item)},
                    onDoubleTap = { onDoubleTap(item) },
                    onTap = { onClick(item) }
                )
            }
    )
    {
        Row() {
            item.imageUrl?.also {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    NetworkImage(
                        context, url = item.imageUrl,
                        Modifier
                            .padding(10.dp)
                            .size(40.dp)
                    )
                }
            }

            Column(Modifier.padding(8.dp)) {
                Text(text = item.name, textAlign = TextAlign.Left, style = Typography.body1)
                Text(text = item.tagline, textAlign = TextAlign.Left, style = Typography.caption)
                Text(
                    text = item.firstBrewed,
                    textAlign = TextAlign.Left,
                    style = Typography.caption
                )
            }
        }
    }
}
