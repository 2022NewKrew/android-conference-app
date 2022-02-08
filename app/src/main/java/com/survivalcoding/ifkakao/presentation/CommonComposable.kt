package com.survivalcoding.ifkakao.presentation

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.domain.model.SessionItem
import com.survivalcoding.ifkakao.presentation.theme.IfKakaoTheme

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
                    .padding(10.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun MainTagCard(str: String = "카카오") {
    IfKakaoTheme {
        Card(
            border = BorderStroke(Dp.Hairline, colorResource(id = R.color.light_yellow)),
            backgroundColor = Color.Black,
            shape = RectangleShape,
            modifier = Modifier.absolutePadding(right = 8.dp)
        ) {
            Text(
                text = str,
                color = colorResource(id = R.color.light_yellow),
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SessionList(sessions: List<SessionItem>) {
    LazyColumn {
        items(sessions) { session ->
            SessionListItem(sessionItem = session)
        }
    }
}

@Composable
fun SessionListItem(sessionItem: SessionItem) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(
                data = Uri.parse(sessionItem.imageUrl),
            ),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier.width(60.dp)
        )
        Column {
            Row {
                MainTagCard(sessionItem.company)
                TagCard(sessionItem.field)
            }
            Text(text = sessionItem.title, color = Color.White)
        }
    }
}