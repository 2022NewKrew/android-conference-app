package com.survivalcoding.ifkakao.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.ifkakao.R


@Composable
fun SharedWithSocialIcon() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            ImageBitmap.imageResource(id = R.drawable.ico_talk),
            modifier = Modifier.size(32.dp),
            contentDescription = "Kakao Talk"
        )
        Image(
            ImageBitmap.imageResource(id = R.drawable.ico_facebook),
            modifier = Modifier.size(32.dp),
            contentDescription = "Kakao Talk"
        )
        Image(
            ImageBitmap.imageResource(id = R.drawable.ico_twit),
            modifier = Modifier.size(32.dp),
            contentDescription = "Kakao Talk"
        )
        Image(
            ImageBitmap.imageResource(id = R.drawable.ico_copy),
            modifier = Modifier.size(32.dp),
            contentDescription = "Kakao Talk"
        )
    }
}