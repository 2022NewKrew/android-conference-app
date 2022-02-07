package com.survivalcoding.ifkakao.compose.widget

import androidx.compose.foundation.border
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.domain.entity.Data
import com.survivalcoding.ifkakao.R

@Composable
fun SingleSession(item: Data) {

    Row(modifier = Modifier.padding(bottom = 5.dp)) {
        Image(
            painter = rememberImagePainter(
                item.linkList?.moImage?.first()?.url ?: R.drawable.thumb
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 96.dp, height = 64.dp)
                .border(
                    1.dp, color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp))
        )
        Box(modifier = Modifier.size(5.dp)) {}
        Column {
            Row(modifier = Modifier.padding(bottom = 5.dp)) {
                BorderedText(text = item.company ?: "카카오", Color.Yellow)
                BorderedText(text = item.field ?: "ALL")
            }
            Text(text = item.title ?: "if Kakao")
        }

    }

    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 10.dp)
    )
}

@Composable
fun BorderedText(text: String, color: Color = Color.White) {
    Text(
        text = text,
        color = color,
        modifier = Modifier
            .padding(end = 5.dp)
            .border(
                width = 1.dp,
                color = color,
            )
            .padding(5.dp)

    )
}