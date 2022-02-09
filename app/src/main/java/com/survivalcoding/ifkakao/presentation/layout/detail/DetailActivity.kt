package com.survivalcoding.ifkakao.presentation.layout.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import com.survivalcoding.ifkakao.domain.model.SessionItem
import com.survivalcoding.ifkakao.presentation.MainLayout
import com.survivalcoding.ifkakao.presentation.SessionListItem
import com.survivalcoding.ifkakao.presentation.TagCard
import com.survivalcoding.ifkakao.presentation.TagItem
import com.survivalcoding.ifkakao.presentation.layout.sessionlist.SessionActivity
import com.survivalcoding.ifkakao.presentation.theme.DarkGrey
import com.survivalcoding.ifkakao.presentation.theme.LightGrey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        val IDX_KEY = "IDX_KEY"
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageLoader = ImageLoader.invoke(this).newBuilder()
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(this@DetailActivity))
                } else {
                    add(GifDecoder())
                }
            }.build()

        val idx = intent.getIntExtra(IDX_KEY, viewModel.getIdxValue() ?: 1)
        viewModel.setIdxValue(idx)

        setContent {
            MainLayout {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 32.dp)
                        .verticalScroll(scrollState)
                ) {
                    VideoWebView(sessionItem = viewModel.sessionItem)
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 16.dp)
                    ) {
                        item {
                            TagCard(viewModel.sessionItem.field)
                        }
                        item {
                            TagCard(viewModel.sessionItem.company)
                        }
                        items(viewModel.sessionItem.classifications) { tag ->
                            TagCard(tag)
                        }
                        items(viewModel.sessionItem.techClassifications) { tag ->
                            TagCard(tag)
                        }
                    }
                    Text(
                        text = viewModel.sessionItem.title, style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(vertical = 32.dp)
                    )
                    Text(text = viewModel.sessionItem.content, style = MaterialTheme.typography.caption)
                    Column(
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        LazyRow {
                            items(viewModel.sessionItem.contentTag) { tag ->
                                TagItem(tag)
                            }
                        }
                    }

                    viewModel.sessionItem.speakerProfiles.forEach { speaker ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clip(
                                        shape = RoundedCornerShape(
                                            percent = 50
                                        )
                                    )
                            ) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = speaker.profileImageUrl,
                                        imageLoader = imageLoader,
                                        builder = {
                                            size(OriginalSize)
                                        }
                                    ),
                                    contentScale = ContentScale.FillWidth,
                                    alignment = Alignment.Center,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(48.dp)
                                        .height(48.dp)
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                Text(
                                    "${speaker.nameKo} ${speaker.nameEn}",
                                    style = MaterialTheme.typography.caption,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    speaker.company ?: "",
                                    style = MaterialTheme.typography.caption,
                                    color = LightGrey
                                )
                                Text(
                                    speaker.occupation ?: "",
                                    style = MaterialTheme.typography.caption,
                                    color = LightGrey
                                )
                            }
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        Button(onClick = {
                            val intent = Intent(this@DetailActivity, SessionActivity::class.java)
                            startActivity(intent)
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = DarkGrey)) {
                            Text("목록보기")
                        }
                        }

                        Text(
                            text = "연관세션",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(vertical = 32.dp)
                        )

                        viewModel.relatedSessionList.forEach { session ->
                            SessionListItem(sessionItem = session, onClick = {
                                val intent = Intent(this@DetailActivity, DetailActivity::class.java)
                                intent.putExtra(IDX_KEY, it)
                                startActivity(intent)
                            })
                            Divider(color = DarkGrey, modifier = Modifier.padding(start = 12.dp, end = 12.dp))
                        }
                    }
                }
            }
        }

        @Composable
        fun VideoWebView(sessionItem: SessionItem?) {
            AndroidView(
                factory =
                {
                    WebView(this).apply {
                        settings.javaScriptEnabled = true
                        loadUrl(sessionItem?.videoUrl ?: "https://tv.kakao.com/embed/player/cliplink/423791694")
                    }
                },
                modifier = Modifier
                    .height(250.dp)
                    .background(LightGrey),
                update =
                {
                    if (sessionItem != null && it.url != sessionItem.videoUrl) {
                        it.loadUrl(sessionItem.videoUrl)
                    }
                }
            )
        }
    }