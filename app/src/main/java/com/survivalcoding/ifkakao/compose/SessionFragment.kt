package com.survivalcoding.ifkakao.compose

import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.domain.entity.ContentsSpeakerList
import com.example.domain.entity.Data
import com.example.domain.entity.Video
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaItem.LiveConfiguration
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.compose.ui.EvanTheme
import com.survivalcoding.ifkakao.compose.widget.BorderedText
import com.survivalcoding.ifkakao.compose.widget.CircleImageLoader
import com.survivalcoding.ifkakao.compose.widget.SharedWithSocialIcon
import com.survivalcoding.ifkakao.compose.widget.SingleSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class SessionFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                EvanTheme {
                    TopCompose(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
// todo viewModel
fun TopCompose(viewModel: MainViewModel) {
    val session = viewModel.session.collectAsState()
    val relatedSessions = viewModel.relatedSessions.collectAsState()
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        //video
        VideoPart(session.value.linkList?.video)
        // description
        DescriptionPart(
            session.value.relationList?.classification,
            session.value.field,
            session.value.company,
            session.value.contentTag,
            session.value.content, session.value.title
        )
        // profile
        ProfilePart(
            url = session.value.linkList?.speakerProfile?.first()?.url,
            item = session.value.contentsSpeakerList?.first()
        )
        // related session
        SessionsPart(relatedSessions)
        ScrollToTop(scrollState, coroutineScope)
    }
}

//https://tv.kakao.com/channel/3693125/cliplink/423791694
@Composable
fun VideoPart(videos: List<Video>?) {
    val context = LocalContext.current

    val player = ExoPlayer.Builder(context)
        .setMediaSourceFactory(
            DefaultMediaSourceFactory(context).setLiveTargetOffsetMs(5000)
        )
        .build()
    val playerView = PlayerView(context)

    // Per MediaItem settings.
    val mediaItem: MediaItem = MediaItem.Builder()
        .setUri(videos?.get(0)?.url ?: "")
        .setLiveConfiguration(
            LiveConfiguration.Builder()
                .setMaxPlaybackSpeed(1.02f)
                .build()
        )
        .build()

    player.setMediaItem(mediaItem)
    playerView.player = player


    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = true

    }

    AndroidView(factory = { playerView })

}


@Composable
fun DescriptionPart(
    classification: List<String>?,
    field: String?,
    company: String?,
    contentTag: String?,
    content: String?,
    title: String?
) {
    FlowRow(crossAxisSpacing = 5.dp) {
        // field
        field?.let {
            BorderedText(text = it)
        }
        // company
        company?.let {
            BorderedText(text = it)
        }
        // classification
        classification?.let {
            for (item in it) {
                BorderedText(text = item)
            }
        }
    }
    // title
    Text(
        text = title ?: "NO DESCRIPTION",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    // content
    Text(text = content ?: "NO CONTENT")

    // contentTag
    contentTag?.let {
        Text(text = it, color = Color.Gray)
    }

}

@Composable
fun ProfilePart(url: String?, item: ContentsSpeakerList?) {
    // profile
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        CircleImageLoader(url = url)
        Column {
            if (item != null) {
                Text(text = item.nameKo + " " + item.nameEn)
            } else {
                Text(text = "Kakao Krew")
            }
            Text(text = item?.company ?: "카카오")
            Text(text = item?.occupation ?: "Krew")
        }
    }
    // social share
    SharedWithSocialIcon()
}


@Composable
fun SessionsPart(sessions: State<List<Data>>) {
    Text(
        text = "연관세션",
        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
    )
    for (item in sessions.value) {
        //todo 겹치는 문제
        key(item.idx) {
            SingleSession(item)
        }
    }

}

@Composable
fun ScrollToTop(scrollState: ScrollState, coroutineScope: CoroutineScope) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "if(kakao)2020 보기")
            Text(text = "kakao corp.")
        }
        Image(
            painterResource(id = com.survivalcoding.ifkakao.R.drawable.ic_baseline_arrow_circle_up_24),
            contentDescription = "description of the image",
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    scrollState.animateScrollTo(0)
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFun() {
    val viewModel: MainViewModel = viewModel()
    TopCompose(viewModel)
}