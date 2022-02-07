package com.survivalcoding.ifkakao.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.survivalcoding.ifkakao.R


@Composable
fun CircleImageLoader(url: String?) {
    if (url != null) {
        Image(
            painter = rememberImagePainter(
                data = url,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
    } else {
        Image(
            painter = rememberImagePainter(R.drawable.thumb),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
    }
}

@Composable
fun RectangleImageLoader(url: String?, _modifier: Modifier = Modifier.size(64.dp)) {
    if (url != null) {
        Image(
            painter = rememberImagePainter(
                data = url,
            ),
            contentDescription = null,
            modifier = _modifier
        )
    } else {
        Image(
            painter = rememberImagePainter(R.drawable.thumb),
            contentDescription = null,
            modifier = _modifier
        )
    }
}