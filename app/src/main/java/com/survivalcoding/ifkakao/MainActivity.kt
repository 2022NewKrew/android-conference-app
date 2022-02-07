package com.survivalcoding.ifkakao

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import com.survivalcoding.ifkakao.presentation.SessionActivity
import dagger.hilt.android.AndroidEntryPoint
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

        val dayList = listOf("Day1", "Day2", "Day3", "All")

        val intent = Intent(this, SessionActivity::class.java)
        startActivity(intent)

        setContent {
        }
    }
}