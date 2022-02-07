package com.survivalcoding.ifkakao

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.survivalcoding.ifkakao.domain.model.SessionItem
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import com.survivalcoding.ifkakao.presentation.SessionActivity
import com.survivalcoding.ifkakao.presentation.SessionList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var sessionRepository: SessionRepository

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
        var sessions = listOf<SessionItem>()
        lifecycleScope.launch(Dispatchers.Main) {
            sessions = sessionRepository.getSessionList() ?: listOf()
        }

        setContent {
            Column {
                TopAppBar(
                    title = { Text(text = "if(kakao)2021") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Menu, "")
                        }
                    },
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
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
                        alpha = 0.2f
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
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            "Highlight",
                            color = Color.White,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.absolutePadding(top = 24.dp, left = 24.dp, bottom = 24.dp),
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
                                Log.d("###MAC", "onCreate: $sessions")
                                startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.dark_grey)),
                            modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            Text(text = "전체 세션 보기", color = Color.White)
                        }
                    }
                }
                Log.d("###MAC", "onCreate: $sessions")

                val coroutineScope = rememberCoroutineScope()

                SessionList(sessions = sessions)
            }
        }
    }
}