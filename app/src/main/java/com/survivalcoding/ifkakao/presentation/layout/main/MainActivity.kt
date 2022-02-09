package com.survivalcoding.ifkakao.presentation.layout.main

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.presentation.MainLayout
import com.survivalcoding.ifkakao.presentation.SessionList
import com.survivalcoding.ifkakao.presentation.layout.detail.DetailActivity
import com.survivalcoding.ifkakao.presentation.layout.sessionlist.SessionActivity
import com.survivalcoding.ifkakao.presentation.theme.DarkGrey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageLoader = ImageLoader.invoke(this).newBuilder()
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(this@MainActivity))
                } else {
                    add(GifDecoder())
                }
            }.build()

        setContent {
            MainLayout {
                Column {
                    SessionList(
                        header = {
                            MainImageCard(imageLoader)
                            HeaderCard()
                        }, sessions = viewModel.sessions,
                        onClick =
                        {
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.IDX_KEY, it)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    private fun HeaderCard() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "Highlight",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.absolutePadding(top = 24.dp, left = 24.dp, bottom = 12.dp),
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
            ) {
                Button(
                    onClick = {
                        val intent = Intent(this@MainActivity, SessionActivity::class.java)
                        startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = DarkGrey),
                    modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    Text(text = "전체 세션 보기")
                }
            }
        }
    }

    @Composable
    private fun MainImageCard(imageLoader: ImageLoader) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = rememberImagePainter(
                    data = R.drawable.index_image,
                    imageLoader = imageLoader
                ),
                contentScale = ContentScale.FillHeight,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                alpha = 0.2f,
            )
            Column {
                Image(
                    painter = rememberImagePainter(
                        data = R.mipmap.bye,
                        imageLoader = imageLoader
                    ),
                    alignment = Alignment.Center,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp),
                )
                Text(
                    text = "if(kakao)2021이\n 여러분의 관심 속에 마무리되었습니다.\n" +
                        "우리, 내년에 또 만나요\n" +
                        "함께 나아가는 더 나은 세상!",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}