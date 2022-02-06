package com.survivalcoding.ifkakao

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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

        setContent {
            Column() {
                TopAppBar(
                    title = { Text(text = "if(kakao)2021") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Menu, "")
                        }
                    },
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
                Row(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                ) {
                    Text(
                        "Session",
                        color = Color.White,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.absolutePadding(top = 24.dp, left = 24.dp, bottom = 24.dp),
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = R.mipmap.main_image,
                            imageLoader = imageLoader
                        ),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DaySelection(items = dayList)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.FilterAlt, "", tint = Color.White)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun DropDownList(
        requestToOpen: Boolean = false,
        list: List<String>,
        request: (Boolean) -> Unit,
        selectedString: (String) -> Unit
    ) {
        DropdownMenu(
            expanded = requestToOpen,
            onDismissRequest = { request(false) },
        ) {
            list.forEach {
                DropdownMenuItem(
                    onClick = {
                        request(false)
                        selectedString(it)
                    }
                ) {
                    Text(it, modifier = Modifier.wrapContentWidth())
                }
            }
        }
    }

    @Composable
    fun DaySelection(items: List<String>) {
        val text = remember { mutableStateOf(items[0]) } // initial value
        val isOpen = remember { mutableStateOf(false) } // initial value
        val openCloseOfDropDownList: (Boolean) -> Unit = {
            isOpen.value = it
        }
        val userSelectedString: (String) -> Unit = {
            text.value = it
        }
        Box(modifier = Modifier.clickable { isOpen.value = true }) {
            Column {
                Text(
                    text = text.value,
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.absolutePadding(top = 24.dp, left = 24.dp, bottom = 24.dp)
                )
                DropDownList(
                    requestToOpen = isOpen.value,
                    list = items,
                    openCloseOfDropDownList,
                    userSelectedString
                )
            }
        }
    }
}