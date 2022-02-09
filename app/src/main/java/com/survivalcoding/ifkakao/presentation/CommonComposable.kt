package com.survivalcoding.ifkakao.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.survivalcoding.ifkakao.domain.model.SessionItem
import com.survivalcoding.ifkakao.presentation.theme.IfKakaoTheme
import com.survivalcoding.ifkakao.presentation.theme.DarkGrey
import com.survivalcoding.ifkakao.presentation.theme.LightGrey
import com.survivalcoding.ifkakao.presentation.theme.LightYellow

@Composable
fun MainLayout(content: @Composable () -> Unit) {
    IfKakaoTheme {
        Scaffold(topBar = { AppBar() }) {
            content()
        }
    }
}

@Preview
@Composable
fun AppBar() {
    IfKakaoTheme {
        TopAppBar(
            title = { Text(text = "if(kakao)2021") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Menu, "")
                }
            },
        )
    }
}

@Composable
@Preview
fun TagCard(str: String = "서비스") {
    IfKakaoTheme {
        Card(
            border = BorderStroke(Dp.Hairline, Color.White), backgroundColor = Color.Black, shape = RectangleShape,
            modifier = Modifier.absolutePadding(right = 8.dp)
        ) {
            Text(
                text = str,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center, style = MaterialTheme.typography.overline
            )
        }
    }
}

@Composable
@Preview
fun MainTagCard(str: String = "카카오") {
    IfKakaoTheme {
        Card(
            border = BorderStroke(Dp.Hairline, LightYellow),
            backgroundColor = Color.Black,
            shape = RectangleShape,
            modifier = Modifier.absolutePadding(right = 8.dp)
        ) {
            Text(
                text = str,
                color = LightYellow,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center, style = MaterialTheme.typography.overline
            )
        }
    }
}

@Composable
fun SessionList(header: @Composable (() -> Unit)? = null, sessions: List<SessionItem>) {
    LazyColumn {
        header?.let {
            item { header() }
        }
        items(sessions) { session ->
            Column {
                SessionListItem(sessionItem = session)
                Divider(color = DarkGrey, modifier = Modifier.padding(start = 12.dp, end = 12.dp))
            }
        }
    }
}

@Composable
fun SessionListItem(sessionItem: SessionItem) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                Log.d("###SLI", sessionItem.mainImageUrl)
            }.padding(12.dp)
    ) {
        Box(
            modifier = Modifier.padding(end = 8.dp).clip(RoundedCornerShape(4.dp, 4.dp, 4.dp, 4.dp))
                .border(1.dp, LightGrey, RoundedCornerShape(4.dp, 4.dp, 4.dp, 4.dp))

        ) {
            Image(
                painter = rememberImagePainter(sessionItem.imageUrl),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp).height(56.dp)
            )
            sessionItem.videoDuration?.let {
                Text(
                    sessionItem.videoDuration,
                    modifier = Modifier.align(Alignment.BottomEnd)
                        .background(DarkGrey)
                        .padding(horizontal = 2.dp),
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row {
                MainTagCard(sessionItem.company)
                TagCard(sessionItem.field)
            }
            Text(text = sessionItem.title, color = Color.White, style = MaterialTheme.typography.caption)
        }
    }
}